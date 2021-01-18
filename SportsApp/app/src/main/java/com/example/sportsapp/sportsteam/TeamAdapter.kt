package com.example.sportsapp.sportsteam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sportsapp.R
import com.example.sportsapp.model.TeamModel
import java.util.ArrayList

class TeamAdapter(private val context: Context, private val list: List<TeamModel>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.team_list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.teamName.text = data.strTeam
        Glide.with(context)
            .load(data.strTeamBadge)
            .apply(RequestOptions().fitCenter())
            .into(holder.teamImage)
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                v?.findNavController()?.navigate(TeamFragmentDirections.actionTeamFragmentToTeamEventsFragment())
            }

        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val teamName = itemView.findViewById<TextView>(R.id.teamTextView)
        val teamImage = itemView.findViewById<ImageView>(R.id.teamImageView)
    }
}