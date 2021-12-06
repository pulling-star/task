package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.SliderItem

class MovieAdapter (private val imageList:ArrayList<SliderItem>):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    inner class MovieViewHolder( itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.iv_image_movie) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val element = imageList[position]
        holder.image.setImageResource(element.image)
    }

    override fun getItemCount(): Int = imageList.size
}