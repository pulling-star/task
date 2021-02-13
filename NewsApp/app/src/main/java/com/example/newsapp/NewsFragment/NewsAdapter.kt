package com.example.newsapp.NewsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.Articles
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter(private val context: Context, private val list: List<Articles>) :RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        val newsDescription= itemView.findViewById<TextView>(R.id.newsDescription)
        val author = itemView.findViewById<TextView>(R.id.author)
        val dateTime = itemView.findViewById<TextView>(R.id.dateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context)
            .load(data.urlToImage)
            .placeholder(R.drawable.newsplace)
            .into(holder.newsImage)
        holder.newsTitle.text= data.title
        holder.newsDescription.text = data.description
        holder.author.text = formatAuthor(data.author.toString())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            holder.dateTime.text = formatDateTime(data.publishedAt)
        }
        val url = data.url
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View) {
                v.findNavController().navigate(NewsFragmentDirections
                    .actionNewsFragmentToWebViewFragment(url))
            }
        })

    }

    private fun formatDateTime(s: String):String {
        val regex = Regex("[A-Z]")
        val dtf: DateTimeFormatter = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_DATE_TIME
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val zdt: ZonedDateTime = ZonedDateTime.parse(s, dtf)
        val str = zdt.toString().replace(regex," ")
        return str
    }

    private fun formatAuthor(author: String): String {
        return if(author == "null"){
            "Author: Unknown"
        }else{
            "Author: $author"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}