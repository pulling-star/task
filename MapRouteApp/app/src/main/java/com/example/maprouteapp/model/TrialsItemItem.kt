package com.example.maprouteapp.model

import java.io.Serializable

data class TrialsItemItem(
    val distance: Double,
    val fareStage: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val seq: Int,
    val time: Any
):Serializable