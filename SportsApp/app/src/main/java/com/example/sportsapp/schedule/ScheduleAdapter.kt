package com.example.sportsapp.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sportsapp.R
import com.example.sportsapp.model.ScheduleModel
import com.example.sportsapp.sportsteam.TeamAdapter


class ScheduleAdapter(private val context: Context, private val list: List<ScheduleModel>)
    :RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ScheduleAdapter.ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.schedule_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.eventName.text = data.strEvent
        holder.homeTeam.text =  data.strHomeTeam
        holder.awayTeam.text = data.strAwayTeam
        holder.homeTeamScore.text =  data.intHomeScore
        holder.awayTeamScore.text = data.intAwayScore
        holder.dateText.text =  data.dateEvent
        holder.venueText.text = data.strVenue
        holder.countryText.text = data.strCountry
        Glide.with(context)
                .load(data.strThumb)
                .placeholder(R.drawable.teamvsteamimage)
                .apply(RequestOptions().fitCenter())
                .into(holder.eventImageView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val eventName = itemView.findViewById<TextView>(R.id.EventnameTextView)
        val homeTeam = itemView.findViewById<TextView>(R.id.homeTeam)
        val awayTeam = itemView.findViewById<TextView>(R.id.awayTeam)
        val homeTeamScore = itemView.findViewById<TextView>(R.id.homeTeamScore)
        val awayTeamScore= itemView.findViewById<TextView>(R.id.awayTeamScore)
        val dateText = itemView.findViewById<TextView>(R.id.dateText)
        val venueText= itemView.findViewById<TextView>(R.id.venueText)
        val countryText= itemView.findViewById<TextView>(R.id.countryText)
        val eventImageView= itemView.findViewById<ImageView>(R.id.eventImageView)
    }
}