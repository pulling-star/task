package com.example.mapdemoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapdemoapp.MainActivity
import com.example.mapdemoapp.R
import com.example.mapdemoapp.databinding.FragmentMapBinding
import com.example.mapdemoapp.repository.MarkerRepo
import com.example.mapdemoapp.viewmodel.MapViewModel
import com.example.mapdemoapp.viewmodel.MapViewModelFactory

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(){

    companion object{
        const val TAG = "MapFragment"
    }

    lateinit var viewModel: MapViewModel
    var list = ArrayList<LatLng>()
    var nameList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map,container,false)

        val repository= MarkerRepo()
        viewModel = ViewModelProvider(this,MapViewModelFactory(repository))
            .get(MapViewModel::class.java)

        val supportMapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment

        supportMapFragment?.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap?) {
                list = (activity as MainActivity).latLngList
                nameList = (activity as MainActivity).latLngname
                var i = 0
                for (item in list) {
                    val markerOptions = MarkerOptions().position(item).title(nameList[i])

                    googleMap?.addMarker(markerOptions)

                    googleMap?.animateCamera(
                        CameraUpdateFactory.zoomTo(
                            18F
                        )
                    )
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLng(item))

                    i++
                }
            }
        })
        return view
    }


}