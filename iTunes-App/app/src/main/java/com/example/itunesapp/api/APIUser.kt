package com.example.itunesapp.api

import com.example.itunesapp.model.SearchResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface APIUser {

    var ENTITY_TYPE_MUSIC_TRACK
        get() = "musicTrack"
        set(value) = TODO()

    @GET("search")
    fun getSearchResults(@QueryMap options: Map<String, String>): Call<SearchResultModel>

}