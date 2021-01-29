package com.example.zomatoapp.restaurants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.model.RestaurantModel

class RestaurantFragment : Fragment() {
    companion object {
        const val TAG = "RestaurantFragment"
    }
    lateinit var restaurantRecyclerViewList:RecyclerView
    lateinit var searchRestaurantEditText:SearchView
    lateinit var restaurantFragmentViewModel: RestaurantFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restaurantFragmentViewModel = ViewModelProvider(this)
            .get(RestaurantFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantRecyclerViewList = view.findViewById(R.id.restaurantRecyclerViewList)
        searchRestaurantEditText = view.findViewById(R.id.searchRestaurantEditText)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        searchRestaurantEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    Log.d(TAG,"query = $query")
                    restaurantFragmentViewModel.CallSearchRestaurantApi(query)
                    restaurantFragmentViewModel.livedataRestaurantSearch.observe(viewLifecycleOwner, Observer {
                        setRecyclerView(it.restaurants)
                    })
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setRecyclerView(list: List<RestaurantModel>) {
        val mAdapter = RestaurantAdapter(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        restaurantRecyclerViewList.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        restaurantRecyclerViewList.itemAnimator = DefaultItemAnimator()
        restaurantRecyclerViewList.adapter = mAdapter
    }
}