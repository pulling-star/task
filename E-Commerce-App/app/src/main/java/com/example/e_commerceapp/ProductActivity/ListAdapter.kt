package com.example.e_commerceapp.ProductActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.R

class ListAdapter(val list:ArrayList<Model>,val itemClickListener: ItemClickListener): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        val image = model.getImage()
        val text = model.getText()
        Glide.with(holder.itemView).load(image).into(holder.imageViewPicture)
        holder.textViewName.text = text
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                itemClickListener.onCLick(model,position)
            }
        })
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageViewPicture = itemView.findViewById<ImageView>(R.id.imageViewPic)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
    }

}