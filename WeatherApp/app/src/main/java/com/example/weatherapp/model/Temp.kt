package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    @Expose
    val day:Double,
    @SerializedName("min")
    @Expose
    val min:Double,
    @SerializedName("max")
    @Expose
    val max:Double,
    @SerializedName("night")
    @Expose
    val night:Double,
    @SerializedName("eve")
    @Expose
    val eve:Double,
    @SerializedName("morn")
    @Expose
    val morn:Double
)