package com.example.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel (
    @SerializedName("status")
    @Expose
    val status:String,
    @SerializedName("totalResults")
    @Expose
    val totalResults:Long,
    @SerializedName("articles")
    @Expose
    val articles:List<Articles>
        )