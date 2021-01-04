package com.example.layouttask

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class ListAdapter(private val list:ArrayList<DataClass>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal val VIEW_TYPE_ONE =1
    internal val VIEW_TYPE_TWO =2

    inner class TitleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val companyNametextView = itemView.findViewById<TextView>(R.id.companyNametextView)
        val companyInfotextView = itemView.findViewById<TextView>(R.id.companyInfotextView)
        val followersTextView = itemView.findViewById<TextView>(R.id.followersTextView)
        val aboutTextView = itemView.findViewById<TextView>(R.id.aboutTextView)
    }

    class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val postTextView = itemView.findViewById<TextView>(R.id.postTextView)
        val dateTextView= itemView.findViewById<TextView>(R.id.dateTextView)
        val profileImage1 = itemView.findViewById<ImageView>(R.id.profileImage1)
        val spinner = itemView.findViewById<Spinner>(R.id.spinner)
        val companyNametextView = itemView.findViewById<TextView>(R.id.companyNametextView)
        val followersTextView = itemView.findViewById<TextView>(R.id.followersTextView)
        val durationTextView = itemView.findViewById<TextView>(R.id.durationTextView)
        val earthImageView = itemView.findViewById<ImageView>(R.id.earthImageView)
        val postedTextView = itemView.findViewById<TextView>(R.id.postedTextView)
        val postedImageView = itemView.findViewById<ImageView>(R.id.postedImageView)
        val companyNametextView1 =itemView.findViewById<TextView>(R.id.companyNametextView1)
        val companyWebsitetextView = itemView.findViewById<TextView>(R.id.companyWebsitetextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        Log.d("ListAdapter","onCreateViewHolder Called",null)
        return when (viewType) {
            VIEW_TYPE_ONE -> TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.first_item_layout,parent,false))
            else ->  PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.second_item_layout,parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("ListAdapter","getItemViewType Called",null)
        if(list.get(position).viewType.equals("firstItem")){
            return VIEW_TYPE_ONE
        }else{
            return VIEW_TYPE_TWO
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is TitleViewHolder){
            holder.companyNametextView.text = list[position].companyName
            holder.companyInfotextView.text = list[position].companyInfo
            holder.followersTextView.text = list[position].followers
            holder.aboutTextView.text = list[position].about
        }else if(holder is PostViewHolder){
            holder.companyNametextView.text = list[position].companyName
            holder.followersTextView.text = list[position].followers
            holder.durationTextView.text=list[position].duration
            holder.postedTextView.text =list[position].about
            holder.companyNametextView1.text=list[position].companyName2
            holder.companyWebsitetextView.text =list[position].websiteName
        }
    }

    override fun getItemCount(): Int {
       Log.d("ListAdapter","getItemCOunt Called",null)
        return list.size
    }
}

