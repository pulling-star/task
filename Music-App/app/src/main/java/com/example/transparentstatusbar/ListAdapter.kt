package com.example.transparentstatusbar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(val list: ArrayList<Model>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_image_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {

        val model = list[position]
        val image = model.getImage()
        val text = model.getText()
        Glide.with(holder.itemView).load(image).into(holder.imageViewPicture)
        holder.textViewName.text = text
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val imageViewPicture = itemView.findViewById<ImageView>(R.id.imageViewPicture)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
    }
}