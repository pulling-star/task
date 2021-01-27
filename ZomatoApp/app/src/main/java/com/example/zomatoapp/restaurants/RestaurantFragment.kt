package com.example.zomatoapp.restaurants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R

class RestaurantFragment : Fragment() {
    companion object {
        const val TAG = "RestaurantFragment"
    }
    lateinit var restaurantRecyclerViewList:RecyclerView
    lateinit var searchRestaurantEditText:SearchView
    lateinit var textview:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantRecyclerViewList = view.findViewById(R.id.restaurantRecyclerViewList)
        searchRestaurantEditText = view.findViewById(R.id.searchRestaurantEditText)
        textview=view.findViewById(R.id.textview)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchRestaurantEditText.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                textview.text = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }

        })
    }
}