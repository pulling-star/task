package com.example.weatherapp.api

import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.BaseModel2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUser {

    @GET("data/2.5/onecall")
    fun getWeather(@Query("lat")lat:String,
                   @Query("lon")lon:String,
                   @Query("exclude")exclude:String,
                   @Query("appid")appid:String) : Call<BaseModel>

    @GET("data/2.5/weather")
    fun getWeatherInLoc(@Query("q")q:String,
                        @Query("appid")appid:String): Call<BaseModel2>

}