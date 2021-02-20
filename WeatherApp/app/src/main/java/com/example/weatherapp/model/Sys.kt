package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("type")
    @Expose
    val type: Long,
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("country")
    @Expose
    val country: String,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Long,
    @SerializedName("sunset")
    @Expose
    val sunset: Long
        )