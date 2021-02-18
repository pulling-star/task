package com.example.enrollmentapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enrollmentapp.database.Users

class UserAdapter(private val context: Context,private val list:List<Users>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context)
           .inflate(R.layout.users_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        val name = data.firstName + data.lastName
        holder.userName.text =name
        holder.userGender.text = data.gender
        holder.userDOB.text = data.dob
        holder.userLocation.text = data.homeTown
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userGender = itemView.findViewById<TextView>(R.id.userGender)
        val userDOB = itemView.findViewById<TextView>(R.id.userDOB)
        val userLocation = itemView.findViewById<TextView>(R.id.userLocation)
    }
}