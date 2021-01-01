package com.example.itunesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itunesapp.R
import com.example.itunesapp.model.ResultModel

class SearchAdapter(private val context: Context, private val searchList: List<ResultModel>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val search = searchList[position]
        holder.musicTitle.text = search.getTrackName()
        holder.musicArtist.text = search.getArtistName()
        Glide.with(context)
            .load(search.getArtworkUrl100())
            .placeholder(R.drawable.musicimage)
            .apply(RequestOptions().fitCenter())
            .into(holder.musicImage)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var musicTitle: TextView
        var musicArtist: TextView
        var musicImage: ImageView

        init {
            musicTitle = view.findViewById(R.id.music_item_title_text)
            musicArtist = view.findViewById(R.id.music_item_author_text)
            musicImage = view.findViewById(R.id.musicImage)
        }
    }


}