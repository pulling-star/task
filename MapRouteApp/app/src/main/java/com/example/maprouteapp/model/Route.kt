package com.example.maprouteapp.model

import java.io.Serializable

data class Route(
    val busRouteDetails: Any,
    val destinationLat: Double,
    val destinationLong: Double,
    val destinationTime: List<String>,
    val destinationTitle: String,
    val distance: Double,
    val duration: String,
    val fare: Int,
    val medium: String,
    val rideEstimation: Any,
    val routeName: Any,
    val sourceLat: Double,
    val sourceLong: Double,
    val sourceTime: List<String>,
    val sourceTitle: String,
    val trails: TrailsItem
):Serializable