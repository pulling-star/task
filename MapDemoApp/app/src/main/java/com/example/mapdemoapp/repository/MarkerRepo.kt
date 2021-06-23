package com.example.mapdemoapp.repository

import com.example.mapdemoapp.network.Retrofit

class MarkerRepo {

    suspend fun getMarkerList() = Retrofit.api.getMarkerList()

}