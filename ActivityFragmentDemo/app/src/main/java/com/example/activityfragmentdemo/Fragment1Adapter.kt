package com.example.activityfragmentdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activityfragmentdemo.databinding.TextLayoutBinding

class Fragment1Adapter(private val context: Context, private  val activity: MainActivity,
                       private val list:ArrayList<String>): RecyclerView.Adapter<Fragment1Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Fragment1Adapter.ViewHolder {
        val binding = TextLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Fragment1Adapter.ViewHolder, position: Int) {
        val data = list[position]
        holder.binding.textview.text = data
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(var binding: TextLayoutBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = HeroListLayoutBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return ViewHolder(binding)
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = differ.currentList[position]
//        holder.binding.apply {
//            heroName.text = data.name
//            Glide.with(context)
//                .load(data.imageurl)
//                .apply(RequestOptions().fitCenter())
//                .into(heroImage)
//        }
//        holder.binding.root.setOnClickListener {
//            val intent = Intent(activity,DetailActivity::class.java)
//            val bundle = Bundle()
//            bundle.putParcelable("data",data)
//            intent.putExtras(bundle)
//            it.context.startActivity(intent)
//        }
//    }
//
//    fun clearData(){
//        list.clear()
//    }
//
//    private val differCallback: DiffUtil.ItemCallback<BaseModelItem> =
//        object : DiffUtil.ItemCallback<BaseModelItem>() {
//            override fun areItemsTheSame(
//                oldItem: BaseModelItem,
//                newItem: BaseModelItem
//            ): Boolean {
//                return newItem.name == oldItem.name
//            }
//
//            override fun areContentsTheSame(
//                oldItem: BaseModelItem,
//                newItem: BaseModelItem
//            ): Boolean {
//                return newItem.name == oldItem.name
//            }
//
//        }
//
//    val differ = AsyncListDiffer(this, differCallback)
//
//    override fun getItemCount() = differ.currentList.size
}