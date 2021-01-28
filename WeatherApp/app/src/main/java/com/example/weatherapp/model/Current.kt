package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Current (
    @SerializedName("dt")
    @Expose
    val dt:Long,
    @SerializedName("sunrise")
    @Expose
    val sunrise:Long?=null,
    @SerializedName("sunset")
    @Expose
    val sunset:Long?=null,
    @SerializedName("temp")
    @Expose
    val temp:Double,
    @SerializedName("feels_like")
    @Expose
    val feels_like:Double,
    @SerializedName("pressure")
    @Expose
    val pressure:Long,
    @SerializedName("humidity")
    @Expose
    val humidity:Long,
    @SerializedName("dew_point")
    @Expose
    val dew_point:Double,
    @SerializedName("uvi")
    @Expose
    val uvi:Double,
    @SerializedName("clouds")
    @Expose
    val clouds:Long,
    @SerializedName("visibility")
    @Expose
    val visibility:Long,
    @SerializedName("wind_speed")
    @Expose
    val wind_speed:Double,
    @SerializedName("wind_deg")
    @Expose
    val wind_deg:Long,
    @SerializedName("weather")
    @Expose
    val weather:List<Weather>,
    @SerializedName("pop")
    @Expose
    val pop:Double?=null
    )