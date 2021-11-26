package com.example.maprouteapp.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.maprouteapp.model.Route
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textview.MaterialTextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

import java.util.ArrayList


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,View.OnClickListener {

    companion object{
        const val TAG = "MapsActivity"
    }
    private lateinit var _map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var routeData : BaseModelItem

    private val _bmSheetRoute : BottomSheetBehavior<View> by lazy { BottomSheetBehavior.from(binding.bmSheetRoute.root as View) }

    private var listOfLatLng = ArrayList<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setUpClickListeners()
        val data = intent.extras?.getSerializable("data")
        routeData = data as BaseModelItem
        _bmSheetRoute.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    private fun setUpClickListeners() {
        binding.bmSheetRoute.cvSourceConstraint.setOnClickListener(this)
        binding.bmSheetRoute.cvRouteConstraint.setOnClickListener(this)
        binding.bmSheetRoute.cvDestConstraint.setOnClickListener(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        _map = googleMap
        _map.mapType = GoogleMap.MAP_TYPE_NORMAL
        _map.clear()
        val list = routeData.routes
        for(route in list){
            _map.addMarker(MarkerOptions().position(LatLng(route.sourceLat,route.sourceLong)).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.layout.marker_layout))))
            _map.addMarker(MarkerOptions().position(LatLng(route.destinationLat,route.destinationLong)).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.layout.marker_layout))))
            if(route.trails != null){
                for(item in route.trails){
                    _map.addMarker(MarkerOptions().position(LatLng(item.latitude,item.longitude)).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.layout.marker_layout))))
                }
            }
        }
        addingLatLngList(routeData)
        drawPolyLineOnMap(listOfLatLng)
        val source = LatLng(routeData.routes[0].sourceLat,routeData.routes[0].sourceLong)
        val destination = LatLng(routeData.routes[routeData.routes.size-1].sourceLat,routeData.routes[routeData.routes.size-1].sourceLong)
        zoomAnimateCamera(source, destination)
    }

    private fun addingLatLngList(routeData: BaseModelItem) {
        for(item in routeData.routes){
            if(item.trails != null){
                for(location in item.trails){
                    listOfLatLng.add(LatLng(location.latitude,location.longitude))
                }
            }else{
                listOfLatLng.add(LatLng(item.sourceLat,item.sourceLong))
                listOfLatLng.add(LatLng(item.destinationLat,item.destinationLong))
            }
        }
    }

    private fun zoomAnimateCamera(source: LatLng,destination:LatLng) {
        val boundsBuilder = LatLngBounds.builder().include(source).include(destination)
        _map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 300))
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.cv_source_constraint -> {
                zoomAnimateCamera(LatLng(routeData.routes[0].sourceLat,routeData.routes[0].sourceLong),LatLng(routeData.routes[0].destinationLat,routeData.routes[0].destinationLong))
            }
            R.id.cv_route_constraint -> {
                zoomAnimateCamera(LatLng(routeData.routes[1].sourceLat,routeData.routes[1].sourceLong),LatLng(routeData.routes[1].destinationLat,routeData.routes[1].destinationLong))
            }
            R.id.cv_dest_constraint -> {
                zoomAnimateCamera(LatLng(routeData.routes[2].sourceLat,routeData.routes[2].sourceLong),LatLng(routeData.routes[2].destinationLat,routeData.routes[2].destinationLong))
            }
        }
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

    private fun createCustomMarker(layoutName: Int): Bitmap {
        try {
            val view: View = (this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(layoutName, null)
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            view.measure(displayMetrics.densityDpi, displayMetrics.densityDpi)
            view.layout(0, 0, 0, 0)
            view.buildDrawingCache()
            val bitmap: Bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            return bitmap
        } catch (e : Exception){
            Log.e(TAG, e.message.toString())
        }
        return Bitmap.createBitmap(0, 0, Bitmap.Config.ARGB_8888)
    }

}