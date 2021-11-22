package com.example.maprouteapp.adapter

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maprouteapp.OnItemClickListener
import com.example.maprouteapp.R
import com.example.maprouteapp.databinding.ItemRouteBinding
import com.example.maprouteapp.model.BaseModel
import com.example.maprouteapp.model.BaseModelItem
import com.example.maprouteapp.ui.MainActivity
import com.example.maprouteapp.ui.MapsActivity
import com.google.android.material.textview.MaterialTextView

class RouteAdapter(private val dataList:MutableList<BaseModelItem>,private val activity:MainActivity):RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    class ViewHolder(var _binding: ItemRouteBinding) : RecyclerView.ViewHolder(_binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRouteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = dataList[position]
        holder._binding.apply {
            tvNextScheduled.text = element.routes[0].sourceTime[0]
            tvEstimatedPrice.text = "$ ${element.totalFare}"
            tvTravelTime.text = element.totalDuration
            tvDistance.text = "${element.totalDistance}km"
        }
        holder.itemView.setOnClickListener{
            val args = Bundle()
            args.putSerializable("data", element)
            val intent = Intent(activity,MapsActivity::class.java)
            intent.putExtra("data",element)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}