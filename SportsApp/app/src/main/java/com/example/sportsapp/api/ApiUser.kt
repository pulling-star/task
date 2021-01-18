package com.example.sportsapp.api

import com.example.sportsapp.model.BaseModel1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUser {

    @GET("v1/json/1/search_all_teams.php?l=Spanish%20La%20Liga")
    fun getTeams() : Call<BaseModel1>
}