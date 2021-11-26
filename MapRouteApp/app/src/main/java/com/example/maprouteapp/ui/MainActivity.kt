package com.example.maprouteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.maprouteapp.R
import com.example.maprouteapp.adapter.RouteAdapter
import com.example.maprouteapp.databinding.ActivityMainBinding
import com.example.maprouteapp.model.BaseModel
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var _binding: ActivityMainBinding
    private lateinit var jsonString: String
    private lateinit var routeData: BaseModel
    private lateinit var adapter: RouteAdapter

    private var isSourceFavClicked = true
    private var isDestinationFavClicked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setUpClickListeners()
        routeData = getRouteDetails()
        setUpAdapter(routeData)
    }

    private fun setUpClickListeners() {
        _binding.ivSourceFavourite.setOnClickListener(this)
        _binding.ivDestinationFavourite.setOnClickListener(this)
    }

    private fun setUpAdapter(routeData: BaseModel) {
        adapter = RouteAdapter(routeData, this)
        _binding.routesRecycler.adapter = adapter
    }

    private fun getRouteDetails(): BaseModel {
        try {
            jsonString = this.assets.open("response.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d(TAG, ioException.toString())
        }
        val gson = Gson()
        return gson.fromJson(jsonString, BaseModel::class.java)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_source_favourite -> {
                isSourceFavClicked = when (isSourceFavClicked) {
                    true -> {
                        _binding.ivSourceFavourite.setImageResource(R.drawable.ic_baseline_favorite_24_filled)
                        false
                    }
                    else -> {
                        _binding.ivSourceFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        true
                    }
                }
            }
            R.id.iv_destination_favourite -> {
                isDestinationFavClicked = when (isDestinationFavClicked) {
                    true -> {
                        _binding.ivDestinationFavourite.setImageResource(R.drawable.ic_baseline_favorite_24_filled)
                        false
                    }
                    else -> {
                        _binding.ivDestinationFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        true
                    }
                }
            }
        }
    }
}