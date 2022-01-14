package com.example.weatherapp_apps10x.model

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListA>,
    val message: Int
)








