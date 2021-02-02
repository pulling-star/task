package com.example.countryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countryapp.R
import com.example.countryapp.model.BaseModel
import com.example.countryapp.model.Languages

class CountryAdapter(private val context: Context, private val list: List<BaseModel>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.country_list_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        Log.d("CountryAdapter", "flag = ${data.flag}")
        Glide.with(context)
            .load(data.flag)
            .apply(RequestOptions().fitCenter())
            .into(holder.flagImage)
        holder.countryName.text = data.name
        holder.capitalTextView.text = formatCapital(data.capital)
        holder.regionTextView.text = formatRegion(data.region)
        holder.subregionTextView.text = formatSubRegion(data.subregion)
        holder.populationTextView.text = formatPopulation(data.population.toString())
        holder.bordersTextView.text = formatBorders(data.borders)
        holder.languagesTextView.text = formatLanguages(data.languages)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flagImage = itemView.findViewById<ImageView>(R.id.flagImage)
        val countryName = itemView.findViewById<TextView>(R.id.countryName)
        val capitalTextView = itemView.findViewById<TextView>(R.id.capitalTextView)
        val regionTextView = itemView.findViewById<TextView>(R.id.regionTextView)
        val subregionTextView = itemView.findViewById<TextView>(R.id.subregionTextView)
        val populationTextView = itemView.findViewById<TextView>(R.id.populationTextView)
        val bordersTextView = itemView.findViewById<TextView>(R.id.bordersTextView)
        val languagesTextView = itemView.findViewById<TextView>(R.id.languagesTextView)
    }

    private fun formatLanguages(languages: List<Languages>): String {
        var languageString = ""
        for (index in languages) {
            if (languageString != "") {
                languageString = "$languageString, ${index.name}"
            } else {
                languageString = index.name
            }
        }
        return "Languages: $languageString"
    }

    private fun formatBorders(borders: List<String>): String {
        var borderString = ""
        for (index in borders) {
            if (borderString != "") {
                borderString = "$borderString, $index"
            } else {
                borderString = index
            }
        }
        return "Borders: $borderString"
    }

    private fun formatPopulation(population: String): String {
        return "Population: $population"
    }

    private fun formatSubRegion(subregion: String): String {
        return "SubRegion: $subregion"
    }

    private fun formatRegion(region: String): String {
        return "Region: $region"
    }

    private fun formatCapital(name: String): String {
        return "Capital: $name"
    }
}