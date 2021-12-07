package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.helpers.OnRecyclerViewItemClickListener
import com.example.moviesapp.model.ResultModel
import com.example.moviesapp.model.SliderItem
import com.example.moviesapp.utils.Constants

class MovieAdapter(
    private val imageList: MutableList<ResultModel> = mutableListOf(),
    private val callback: OnRecyclerViewItemClickListener<ResultModel>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_image_movie) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val element = imageList[position]
        Glide.with(holder.itemView.context)
            .load("${Constants.IMAGE_API}${element.posterPath}")
            .into(holder.image)
        holder.itemView.setOnClickListener {
            callback.invoke(holder.itemView, position, element)
        }
    }

    override fun getItemCount(): Int = imageList.size

    fun updateDataSet(movies: List<ResultModel>, pageCount: Int) {
        if (pageCount == 1) {
            imageList.clear()
        }
        imageList.addAll(movies)
        notifyItemRangeChanged(0, imageList.size)
    }
}