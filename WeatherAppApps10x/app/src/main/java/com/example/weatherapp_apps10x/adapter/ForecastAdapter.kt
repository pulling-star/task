package com.example.weatherapp_apps10x.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp_apps10x.R
import com.example.weatherapp_apps10x.model.ForecastModel
import com.google.android.material.textview.MaterialTextView

class ForecastAdapter(private val weatherList:MutableList<ForecastModel> = mutableListOf()): RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val day = itemView.findViewById(R.id.tv_day) as MaterialTextView
        val temperature = itemView.findViewById(R.id.tv_forecast_temp) as MaterialTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = weatherList[position]
        holder.apply {
            day.text = element.day
            temperature.text = element.temperature
        }
    }

    override fun getItemCount(): Int = weatherList.size

    fun updateList(list:List<ForecastModel>){
        weatherList.clear()
        weatherList.addAll(list)
        notifyItemRangeChanged(0,weatherList.size)
    }

}