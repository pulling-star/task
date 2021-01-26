package com.example.sportsapp.api

import com.example.sportsapp.model.BaseModel1
import com.example.sportsapp.model.BaseModel2
import com.example.sportsapp.model.TeamModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUser {

    @GET("v1/json/1/search_all_teams.php?l=Spanish%20La%20Liga")
    fun getTeams() : Call<BaseModel1>

    @GET("v1/json/1/lookupteam.php")
    fun getTeamDetails(@Query("id") id:String):Call<BaseModel1>

    @GET("v1/json/1/searchteams.php")
    fun getTeamSearchDetails(@Query("t") teamName:String):Call<BaseModel1>

    @GET("v1/json/1/eventslast.php")
    fun getScheduleDetails(@Query("id")id:String):Call<BaseModel2>
}