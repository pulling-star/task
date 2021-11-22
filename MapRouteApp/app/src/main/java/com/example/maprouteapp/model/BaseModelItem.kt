package com.example.maprouteapp.model

import java.io.Serializable

data class BaseModelItem(
    val routeTitle: String,
    val routes: List<Route>,
    val totalDistance: Double,
    val totalDuration: String,
    val totalFare: Int
):Serializable