package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesapp.R
import com.example.moviesapp.model.SliderItem

class SliderAdapter( val viewPager: ViewPager2,val imageList:ArrayList<SliderItem>):RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.iv_image_slider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item,parent,false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val element = imageList[position]
        holder.image.setImageResource(element.image)
//        if (position == imageList.size - 2){
//            viewPager.post(run)
//        }
    }
//    val run = Runnable {
//        imageList.addAll(imageList)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int = imageList.size
}