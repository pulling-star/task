package com.example.foodblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.foodblogs.R
import com.example.foodblogs.model.Card
import com.example.foodblogs.utils.Extensions.setImage
import com.google.android.material.textview.MaterialTextView

class FoodAdapter(private val foodList:MutableList<Card> = mutableListOf()): RecyclerView.Adapter<FoodAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val foodImage = itemView.findViewById<ImageView>(R.id.iv_food_one)
        val foodTitle = itemView.findViewById<MaterialTextView>(R.id.tv_food_one_title)
        val foodDescription = itemView.findViewById<MaterialTextView>(R.id.tv_food_one_description)
        val info = itemView.findViewById<MaterialTextView>(R.id.tv_about)
        val location = itemView.findViewById<MaterialTextView>(R.id.tv_where)
        val bestDishTitle = itemView.findViewById<MaterialTextView>(R.id.tv_best_dishes_title)
        val bestDish = itemView.findViewById<MaterialTextView>(R.id.tv_best_dishes)
        val photoRecycler = itemView.findViewById<RecyclerView>(R.id.photo_recycler)
        val linearView = itemView.findViewById<LinearLayout>(R.id.ll_food_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = foodList[position]
        holder.apply {
            foodImage.setImage(element.img)
            foodTitle.text = element.title
            foodDescription.text = element.desc
            if(element.details.about.isNotEmpty()){
                info.text = element.details.about[0]
            }
            location.text = element.details.where[0].name
            bestDishTitle.text = "BEST" + element.title + "DISHES"
            if(element.details.dishes.isNotEmpty()){
                bestDish.text = element.details.dishes[0]
            }
            itemView.setOnClickListener {
                when(linearView.visibility){
                    View.VISIBLE -> linearView.visibility = View.GONE
                    View.GONE -> linearView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int = foodList.size

    fun updateList(list:List<Card>){
        foodList.clear()
        foodList.addAll(list)
        notifyItemRangeChanged(0,foodList.size)
    }
}