package com.example.marvelapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapp.DetailActivity
import com.example.marvelapp.MainActivity
import com.example.marvelapp.databinding.HeroListLayoutBinding
import com.example.marvelapp.model.BaseModelItem
import java.util.ArrayList

class HeroAdapter(private val context: Context,private  val activity: MainActivity,
private val list:ArrayList<BaseModelItem>): RecyclerView.Adapter<HeroAdapter.ViewHolder>()  {

    class ViewHolder(var binding: HeroListLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HeroListLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            heroName.text = data.name
            Glide.with(context)
                .load(data.imageurl)
                .apply(RequestOptions().fitCenter())
                .into(heroImage)
        }
        holder.binding.root.setOnClickListener {
            val intent = Intent(activity,DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("data",data)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }
    }

    fun clearData(){
        list.clear()
    }

    private val differCallback: DiffUtil.ItemCallback<BaseModelItem> =
        object : DiffUtil.ItemCallback<BaseModelItem>() {
            override fun areItemsTheSame(
                oldItem: BaseModelItem,
                newItem: BaseModelItem
            ): Boolean {
                return newItem.name == oldItem.name
            }

            override fun areContentsTheSame(
                oldItem: BaseModelItem,
                newItem: BaseModelItem
            ): Boolean {
                return newItem.name == oldItem.name
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount() = differ.currentList.size
}