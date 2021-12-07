package com.example.moviesapp.network

import com.example.moviesapp.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET("discover/movie")
    suspend fun getMovieResults(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<BaseModel>

}