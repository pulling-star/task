package com.example.mapdemoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapdemoapp.R
import com.example.mapdemoapp.adapter.MarkerListAdapter
import com.example.mapdemoapp.databinding.FragmentMarkerListBinding
import com.example.mapdemoapp.model.EndModel
import com.example.mapdemoapp.model.Response
import com.example.mapdemoapp.repository.MarkerRepo
import com.example.mapdemoapp.viewmodel.MapViewModel
import com.example.mapdemoapp.viewmodel.MapViewModelFactory

class MarkerListFragment: Fragment(R.layout.fragment_marker_list) {

    companion object{
        const val TAG = "MarkerListFragment"
    }

    lateinit var binding: FragmentMarkerListBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter:MarkerListAdapter
    lateinit var viewModel:MapViewModel


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
            adapter.differ.submitList(it)
        })
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        adapter = MarkerListAdapter(this.requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.markerListRecyclerView.layoutManager = linearLayoutManager
        binding.markerListRecyclerView.adapter = adapter
    }

}