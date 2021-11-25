package com.example.maprouteapp.helpers

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.maprouteapp.R
import com.example.maprouteapp.TaskLoadedCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class ParserTask(task: TaskLoadedCallback, private val ctx : Context) : AsyncTask<String?, Int?, List<List<HashMap<String, String>>>?>() {

    private val taskCallback = task

    override fun doInBackground(vararg params: String?): List<List<HashMap<String, String>>>? {
        val jObject: JSONObject
        var routes: List<List<HashMap<String, String>>>? = null
        try {
            jObject = JSONObject(params[0].toString())
            routes = JsonParserDirections().parse(jObject)
            Log.d("parser", routes.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ParserTaskBg:",e.message.toString())
        }
        return routes
    }

    override fun onPostExecute(result: List<List<HashMap<String, String>>>?) {
        var lineOptions: PolylineOptions? = null
        for (i in result!!.indices) {
            val points = ArrayList<LatLng>()
            lineOptions = PolylineOptions()
            val path = result[i]
            for (j in path.indices) {
                val point = path[j]
                val lat = (point["lat"])!!.toDouble()
                val lng = (point["lng"])!!.toDouble()
                val position = LatLng(lat, lng)
                points.add(position)
            }
            lineOptions.addAll(points)
            lineOptions.width(20f)
            lineOptions.color(ContextCompat.getColor(ctx, android.R.color.holo_blue_dark))
            lineOptions.geodesic(true)
        }
        // Drawing polyline in the Google Map for the i-th route
        if (lineOptions != null) {
            taskCallback.onTaskDone(lineOptions)
        }
    }
}