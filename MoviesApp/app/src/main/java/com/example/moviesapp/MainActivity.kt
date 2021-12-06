package com.example.moviesapp


import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesapp.adapter.MovieAdapter
import com.example.moviesapp.adapter.SliderAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.helpers.HorizontalMarginItemDecoration

import com.example.moviesapp.model.SliderItem
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }
    private lateinit var _binding : ActivityMainBinding

    private lateinit var sliderItemlist: ArrayList<SliderItem>
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setUpViewPagerandAdapter()
        addingViewPagerItems()
    }

    private fun addingViewPagerItems() {
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
        sliderItemlist.add(SliderItem(R.drawable.ic_launcher_background))
    }

    private fun setUpViewPagerandAdapter() {
        sliderItemlist = ArrayList()
        sliderAdapter = SliderAdapter(sliderItemlist)
        _binding.viewPager.adapter = sliderAdapter
        _binding.viewPager.clipToPadding = false
        _binding.viewPager.clipChildren = false
        _binding.viewPager.offscreenPageLimit = 3
        _binding.viewPager.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer{ page,position ->
            val r:Float= 1 - abs(position)
            page.scaleY = 0.85f + r * 0.20f
        }
        _binding.viewPager.setPageTransformer(comPosPageTarn)

        movieAdapter = MovieAdapter(sliderItemlist)
        _binding.movieRecycler.layoutManager = GridLayoutManager(this,3)
        _binding.movieRecycler.adapter = movieAdapter
    }

}