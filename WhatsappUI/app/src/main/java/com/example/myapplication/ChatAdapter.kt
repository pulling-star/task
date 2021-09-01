package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemChatLayoutBinding

class ChatAdapter(private val context: Context): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemChatLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            name.text = data.name
            message.text = data.message
            time.text = data.time
            Glide.with(context)
                .load(data.image)
                .centerCrop()
                .into(imageView)
        }
    }

    private val differCallback: DiffUtil.ItemCallback<ChatItemModel> =
        object : DiffUtil.ItemCallback<ChatItemModel>() {
            override fun areItemsTheSame(
                oldItem: ChatItemModel,
                newItem: ChatItemModel
            ): Boolean {
                return newItem.name == oldItem.name
            }

            override fun areContentsTheSame(
                oldItem: ChatItemModel,
                newItem: ChatItemModel
            ): Boolean {
                return newItem.name == oldItem.name
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}