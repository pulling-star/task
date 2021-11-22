package com.example.maprouteapp.ui

import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.maprouteapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.maprouteapp.databinding.ActivityMapsBinding
import com.example.maprouteapp.model.BaseModel
import com.example.maprouteapp.model.BaseModelItem
import com.google.gson.Gson
import java.io.IOException
import com.google.android.gms.maps.CameraUpdate

import com.google.android.gms.maps.model.LatLngBounds

import com.google.android.gms.maps.model.PolylineOptions
import java.util.ArrayList


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var routeData : BaseModelItem

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
        mMap = googleMap
        val list = routeData.routes
        for(route in list){
            mMap.addMarker(MarkerOptions().position(LatLng(route.sourceLat,route.sourceLong)))
            mMap.addMarker(MarkerOptions().position(LatLng(route.destinationLat,route.destinationLong)))
        }
        listOfLatLng.add(LatLng(routeData.routes[0].sourceLat,routeData.routes[0].sourceLong))
        listOfLatLng.add(LatLng(routeData.routes[routeData.routes.size-1].destinationLat,routeData.routes[routeData.routes.size-1].destinationLong))
        drawPolyLineOnMap(listOfLatLng)
        val myLocation = LatLng(routeData.routes[0].sourceLat,routeData.routes[0].sourceLong)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10f))
    }

    private fun drawPolyLineOnMap(list: List<LatLng?>) {
        val polyOptions = PolylineOptions()
        polyOptions.color(ContextCompat.getColor(this,android.R.color.holo_blue_light))
        polyOptions.width(5f)
        polyOptions.addAll(list)
        mMap.addPolyline(polyOptions)
        val builder = LatLngBounds.Builder()
        for (latLng in list) {
            if (latLng != null) {
                builder.include(latLng)
            }
        }
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
        mMap.animateCamera(cu)
    }
}