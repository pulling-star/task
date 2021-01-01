package com.example.itunesapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResultModel {

    @SerializedName("resultCount")
    @Expose
    var resultCount:Int? = null
    @SerializedName("results")
    @Expose
    var resultModels: List<ResultModel>? = null

    public fun getResultCount() {
        resultCount
    }

    public fun setResultCount(resultCount:Int){
        this.resultCount = resultCount
    }

    public fun getResultModels(){
        resultModels
    }

    @JvmName("setResultModels1")
    public fun setResultModels(resultModels: List<ResultModel>){
        this.resultModels = resultModels
    }

}