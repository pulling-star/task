package com.example.sportsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel2(
        @SerializedName("results")
        @Expose
        val results :List<ScheduleModel>
)