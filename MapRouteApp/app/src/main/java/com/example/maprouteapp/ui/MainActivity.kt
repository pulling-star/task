package com.example.maprouteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.maprouteapp.OnItemClickListener
import com.example.maprouteapp.adapter.RouteAdapter
import com.example.maprouteapp.databinding.ActivityMainBinding
import com.example.maprouteapp.model.BaseModel
import com.example.maprouteapp.model.BaseModelItem
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityMainBinding
    private lateinit var jsonString: String
    private lateinit var routeData : BaseModel
    private lateinit var adapter: RouteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        routeData = getRouteDetails()
        setUpAdapter(routeData)
    }

    private fun setUpAdapter(routeData: BaseModel) {
        adapter = RouteAdapter(routeData,this)
        _binding.routesRecycler.adapter = adapter
    }

    private fun getRouteDetails(): BaseModel {
        try {
            jsonString = this.assets.open("response.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("MainActivity", ioException.toString())
        }
        val gson = Gson()
        return gson.fromJson(jsonString, BaseModel::class.java)
    }
}