package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Daily (
    @SerializedName("dt")
    @Expose
    val dt:Long,
    @SerializedName("sunrise")
    @Expose
    val sunrise:Long,
    @SerializedName("sunset")
    @Expose
    val sunset:Long,
    @SerializedName("temp")
    @Expose
    val temp:Temp,
    @SerializedName("feels_like")
    @Expose
    val feels_like:FeelsLike,
    @SerializedName("pressure")
    @Expose
    val pressure:Long,
    @SerializedName("humidity")
    @Expose
    val humidity:Long,
    @SerializedName("dew_point")
    @Expose
    val dew_point:Double,
    @SerializedName("wind_speed")
    @Expose
    val wind_speed:Double,
    @SerializedName("wind_deg")
    @Expose
    val wind_deg:Long,
    @SerializedName("weather")
    @Expose
    val weather:List<Weather>,
    @SerializedName("clouds")
    @Expose
    val clouds:Long,
    @SerializedName("pop")
    @Expose
    val pop:Double,
    @SerializedName("uvi")
    @Expose
    val uvi:Double,
    @SerializedName("rain")
    @Expose
    val rain:Double?=null
        )