package com.example.maprouteapp.ui

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.maprouteapp.R
import com.example.maprouteapp.TaskLoadedCallback

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.maprouteapp.databinding.ActivityMapsBinding
import com.example.maprouteapp.helpers.DownloadTask
import com.example.maprouteapp.model.BaseModelItem
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

import java.util.ArrayList


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,View.OnClickListener {

    private lateinit var _map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var routeData : BaseModelItem
    private val _bmSheetRoute : BottomSheetBehavior<View> by lazy { BottomSheetBehavior.from(binding.bmSheetRoute as View) }

    private var listOfLatLng = ArrayList<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val data = intent.extras?.getSerializable("data")

        routeData = data as BaseModelItem

    }

    override fun onMapReady(googleMap: GoogleMap) {
        _map = googleMap
        _map.mapType = GoogleMap.MAP_TYPE_NORMAL
        _map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
        val list = routeData.routes
        for(route in list){
            _map.addMarker(MarkerOptions().position(LatLng(route.sourceLat,route.sourceLong)))
            _map.addMarker(MarkerOptions().position(LatLng(route.destinationLat,route.destinationLong)))
        }
        listOfLatLng.add(LatLng(routeData.routes[0].sourceLat,routeData.routes[0].sourceLong))
        listOfLatLng.add(LatLng(routeData.routes[routeData.routes.size-1].destinationLat,routeData.routes[routeData.routes.size-1].destinationLong))
        drawPolyLineOnMap(listOfLatLng)
        val myLocation = LatLng(routeData.routes[0].sourceLat,routeData.routes[0].sourceLong)
        _map.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
        _map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10f))

    }

    private fun drawPolyLineOnMap(list: List<LatLng?>) {
        val polyOptions = PolylineOptions()
        polyOptions.color(ContextCompat.getColor(this,android.R.color.holo_blue_light))
        polyOptions.width(5f)
        polyOptions.addAll(list)
        _map.addPolyline(polyOptions)
        val builder = LatLngBounds.Builder()
        for (latLng in list) {
            if (latLng != null) {
                builder.include(latLng)
            }
        }
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
        _map.animateCamera(cu)
    }

    override fun onClick(v: View) {

    }

}