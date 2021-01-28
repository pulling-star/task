package com.example.weatherapp.api

import com.example.weatherapp.model.BaseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiUser {

    @GET("data/2.5/onecall?lat=10.7905&lon=78.7047&exclude=minutely&appid=c8388944e2520aa17b43da2e294ed346")
    fun getWeather() : Call<BaseModel>
}