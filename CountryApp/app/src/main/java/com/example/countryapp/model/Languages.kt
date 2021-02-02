package com.example.countryapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Languages (
    @SerializedName("iso639_1")
    @Expose
    val iso639_1 : String? = null,
    @SerializedName("iso639_2")
    @Expose
    val iso639_2 : String? = null,
    @SerializedName("name")
    @Expose
    val name : String,
    @SerializedName("nativeName")
    @Expose
    val nativeName : String
        )