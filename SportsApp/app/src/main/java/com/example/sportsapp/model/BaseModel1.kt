package com.example.sportsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel1 (
    @SerializedName("teams")
    @Expose
    val teams:List<TeamModel>
    )