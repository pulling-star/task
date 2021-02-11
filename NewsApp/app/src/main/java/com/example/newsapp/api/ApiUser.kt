package com.example.newsapp.api

import com.example.newsapp.model.BaseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUser {

    @GET("v2/top-headlines?q=india&apiKey=a1f616c6388a4a74ad396c4e42caaff8")
    fun getNews(@Query("q") q:String, @Query("apiKey") apiKey:String): Call<BaseModel>
}