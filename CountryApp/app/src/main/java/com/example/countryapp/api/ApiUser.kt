package com.example.countryapp.api

import com.example.countryapp.model.BaseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiUser {

    @GET("rest/v2/region/Asia")
    fun getCountries() : Call<List<BaseModel>>

}