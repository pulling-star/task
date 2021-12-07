package com.example.moviesapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

data class BaseModel(
    @SerializedName("page")
    @Expose
    val page: String,
    @SerializedName("total_pages")
    @Expose
    val totalPages: String,
    @SerializedName("total_results")
    @Expose
    val totalResults: String,
    @SerializedName("results")
    @Expose
    val results: ArrayList<ResultModel>
) : Serializable