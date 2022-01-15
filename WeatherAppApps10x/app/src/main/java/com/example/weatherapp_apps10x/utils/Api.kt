package com.example.weatherapp_apps10x.utils

import com.example.weatherapp_apps10x.model.ForecastResponse
import com.example.weatherapp_apps10x.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("data/2.5/weather")
    suspend fun getWeatherDetails(@Query("q") q:String,
                                  @Query("APPID") appId:String): Response<WeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getForecastDetails(@Query("q") q:String,
                                  @Query("APPID") appId:String): Response<ForecastResponse>
}