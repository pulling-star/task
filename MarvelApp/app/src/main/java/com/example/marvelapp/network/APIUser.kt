package com.example.marvelapp.network

import com.example.marvelapp.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIUser {
    @GET("demos/marvel/")
    suspend fun getHeroResults(): Response<BaseModel>
}