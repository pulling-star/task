package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.Daily
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(private  val context:Context, private val list:List<Daily>):
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.day_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        val unixSeconds = data.dt
        val date = Date(unixSeconds*1000L)
        val dateFormat = SimpleDateFormat("EEEE,dd HH:mm",)
        val dateString = dateFormat.format(date)+"AM"
        holder.dateTime.text=dateString
        val temp = data.temp.min - 273
        val tempStringMin = String.format("%.0f",temp)+"°C"
        holder.minTemp.text = tempStringMin
        val tempmax = data.temp.max - 273
        val tempStringMax = String.format("%.0f",tempmax)+"°C"
        holder.maxTemp.text = tempStringMax
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val dateTime = itemView.findViewById<TextView>(R.id.dateTextView)
        val minTemp = itemView.findViewById<TextView>(R.id.minTempTextView)
        val maxTemp = itemView.findViewById<TextView>(R.id.maxTempTextView)

    }



}