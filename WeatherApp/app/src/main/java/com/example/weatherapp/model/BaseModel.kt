package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel (
    @SerializedName("lat")
    @Expose
    val lat:Double,
    @SerializedName("lon")
    @Expose
    val lon:Double,
    @SerializedName("timezone")
    @Expose
    val timezone:String,
    @SerializedName("timezone_offset")
    @Expose
    val timezone_offset:Long,
    @SerializedName("current")
    @Expose
    val current:Current,
    @SerializedName("hourly")
    @Expose
    val hourly:List<Current>,
    @SerializedName("daily")
    @Expose
    val daily:List<Daily>
        )