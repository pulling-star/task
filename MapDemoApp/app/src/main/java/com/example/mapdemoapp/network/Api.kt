package com.example.mapdemoapp.network

import com.example.mapdemoapp.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/v2/marker/getMarkers/index.php")
    suspend fun getMarkerList(): Response<BaseModel>

}