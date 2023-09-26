package com.example.contentlistapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contentlistapp.databinding.ItemContentLayoutBinding
import com.example.contentlistapp.models.Content
import com.example.contentlistapp.utils.Extensions.loadImageViewWithDrawable
import com.example.contentlistapp.utils.Utility

class ContentListAdapter(private var list: List<Content>): RecyclerView.Adapter<ContentListAdapter.ContentViewHolder>() {

    class ContentViewHolder(var binding: ItemContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(ItemContentLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val element = list[position]
        holder.binding.tvContentName.text = element.name
        holder.binding.ivContentImage.loadImageViewWithDrawable(Utility.getMappedImage(element.posterImage))
    }

    fun setList(newList:List<Content>){
        val oldSize = list.size
        list = newList
        notifyItemRangeChanged(oldSize-1,list.size-1)
    }

}