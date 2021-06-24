package com.example.mapdemoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapdemoapp.MainActivity
import com.example.mapdemoapp.R
import com.example.mapdemoapp.adapter.MarkerListAdapter
import com.example.mapdemoapp.databinding.FragmentMarkerListBinding
import com.example.mapdemoapp.model.EndModel
import com.example.mapdemoapp.model.Response
import com.example.mapdemoapp.repository.MarkerRepo
import com.example.mapdemoapp.viewmodel.MapViewModel
import com.example.mapdemoapp.viewmodel.MapViewModelFactory
import com.google.android.gms.maps.model.LatLng

class MarkerListFragment: Fragment(R.layout.fragment_marker_list) {

    companion object{
        const val TAG = "MarkerListFragment"
    }

    lateinit var binding: FragmentMarkerListBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter:MarkerListAdapter
    lateinit var viewModel:MapViewModel
    var latLngList = ArrayList<LatLng>()
    var nameList = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMarkerListBinding.bind(view)

        val repository= MarkerRepo()
        viewModel = ViewModelProvider(this,MapViewModelFactory(repository))
            .get(MapViewModel::class.java)

        setUpRecyclerView()
        getMarkerList()
    }

    private fun getMarkerList() {
        viewModel.getMarkerList()
        viewModel.markerResponse.observe(viewLifecycleOwner, Observer {
            Log.d(TAG,"response = ${it.toString()}")
            addLatLngToList(it)
            addNameToList(it)
            adapter.differ.submitList(it)
        })
    }

    private fun addNameToList(list: ArrayList<EndModel>) {
        for(item in list){
            val name = item.name
            nameList.add(name)
        }
        (activity as MainActivity).latLngname = nameList
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        adapter = MarkerListAdapter(this.requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.markerListRecyclerView.layoutManager = linearLayoutManager
        binding.markerListRecyclerView.adapter = adapter
    }

    private fun addLatLngToList(list: ArrayList<EndModel>) {

        for(item in list){
            val lat = item.latitude.toDouble()
            val lng = item.longitude.toDouble()
            val LatLng = LatLng(lat,lng)
            latLngList.add(LatLng)
        }
        (activity as MainActivity).latLngList = latLngList
    }
}