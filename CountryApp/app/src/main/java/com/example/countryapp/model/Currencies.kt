package com.example.countryapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currencies (
        @SerializedName("code")
        @Expose
        val code : String? = null,
        @SerializedName("name")
        @Expose
        val name : String? = null,
        @SerializedName("symbol")
        @Expose
        val symbol : String? = null

        )