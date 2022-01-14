package com.example.weatherapp_apps10x.utils

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Extensions {

    fun Context.convertKelvinToCelsius(temperature:Double):String{
        return "${(temperature-273).toInt()}°"
    }

    fun Context.getDayFromDate(date:String):String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = sdf.parse(date)
        return DateFormat.format("EEEE",date) as String
    }
}