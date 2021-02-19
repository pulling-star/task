package com.example.weatherapp.api

import com.example.weatherapp.model.BaseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUser {

    @GET("data/2.5/onecall")
    fun getWeather(@Query("lat")lat:String,
                   @Query("lon")lon:String,
                   @Query("exclude")exclude:String,
                   @Query("appid")appid:String) : Call<BaseModel>

}