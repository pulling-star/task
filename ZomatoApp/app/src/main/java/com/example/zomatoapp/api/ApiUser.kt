package com.example.zomatoapp.api

import com.example.zomatoapp.model.BaseModel1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUser {

    @GET("v2.1/search?entity_id=11332&entity_type=city&q=oyalo")
    fun getRestaurants(@Query("q")query:String) : Call<BaseModel1>
}