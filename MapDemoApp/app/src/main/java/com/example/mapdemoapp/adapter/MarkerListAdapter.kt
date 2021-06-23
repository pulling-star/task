package com.example.mapdemoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapdemoapp.databinding.MarkerListLayoutBinding
import com.example.mapdemoapp.model.EndModel

class MarkerListAdapter(private val context: Context):RecyclerView.Adapter<MarkerListAdapter.ViewHolder>() {

    class ViewHolder(var binding: MarkerListLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarkerListAdapter.ViewHolder {
        val binding = MarkerListLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: MarkerListAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            name.text = data.name
            latitude.text = data.latitude
            longitude.text = data.longitude
        }
    }

    private val differCallback: DiffUtil.ItemCallback<EndModel> =
        object : DiffUtil.ItemCallback<EndModel>() {
            override fun areItemsTheSame(
                oldItem: EndModel,
                newItem: EndModel
            ): Boolean {
                return newItem.name == oldItem.name
            }

            override fun areContentsTheSame(
                oldItem: EndModel,
                newItem: EndModel
            ): Boolean {
                return newItem.name == oldItem.name
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount() = differ.currentList.size

}