package com.example.countryapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegionalBlocs (
    @SerializedName("acronym")
    @Expose
    val acronym: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("otherAcronyms")
    @Expose
    val otherAcronyms: List<Any?>,
    @SerializedName("otherNames")
    @Expose
    val otherNames: List<Any?>
        )