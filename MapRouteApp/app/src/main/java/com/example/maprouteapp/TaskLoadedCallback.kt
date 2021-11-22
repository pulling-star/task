package com.example.maprouteapp

import com.google.android.gms.maps.model.PolylineOptions

interface TaskLoadedCallback {
    fun onTaskDone(values: PolylineOptions)
}