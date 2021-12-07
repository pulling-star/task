package com.example.moviesapp


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.moviesapp.adapter.MovieAdapter
import com.example.moviesapp.adapter.SliderAdapter
import com.example.moviesapp.databinding.ActivityMainBinding

import com.example.moviesapp.model.SliderItem
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.viewmodel.MovieViewModel
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var movieViewModel: MovieViewModel

    private var sliderItemlist = ArrayList<SliderItem>()
    private var _pageCount = 1

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(mutableListOf()) { _, _, movieData ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("ResultData", movieData)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        setUpSliderViewPager()
        setUpRecyclerViewAdapter()
        getMovieFromApi(_pageCount)
        addingSliderImages()
        nestedScrollViewListener()
    }

    private fun setUpSliderViewPager() {
        sliderAdapter = SliderAdapter(sliderItemlist)
        _binding.viewPager.adapter = sliderAdapter
        _binding.viewPager.clipToPadding = false
        _binding.viewPager.clipChildren = false
        _binding.viewPager.offscreenPageLimit = 3
        _binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.20f
        }
        _binding.viewPager.setPageTransformer(comPosPageTarn)
    }

    private fun setUpRecyclerViewAdapter() {
        _binding.movieRecycler.layoutManager = GridLayoutManager(this, 3)
        _binding.movieRecycler.adapter = movieAdapter
    }

    private fun getMovieFromApi(pageCount: Int) {
        _binding.progressBar.visibility = View.VISIBLE
        movieViewModel.getMoviesList(pageCount).observe(this, Observer { movieList ->
            _binding.progressBar.visibility = View.GONE
            val result = movieList.results
            movieAdapter.updateDataSet(result, pageCount)
            _pageCount = Integer.parseInt(movieList.page) + 1
        })
    }

    private fun addingSliderImages() {
        sliderItemlist.add(SliderItem("${Constants.IMAGE_API}/lNyLSOKMMeUPr1RsL4KcRuIXwHt.jpg"))
        sliderItemlist.add(SliderItem("${Constants.IMAGE_API}/5uVhMGsps81CN0S4U9NF0Z4tytG.jpg"))
        sliderItemlist.add(SliderItem("${Constants.IMAGE_API}/xGrTm3J0FTafmuQ85vF7ZCw94x6.jpg"))
        sliderItemlist.add(SliderItem("${Constants.IMAGE_API}//zBkHCpLmHjW2uVURs5uZkaVmgKR.jpg"))
        sliderItemlist.add(SliderItem("${Constants.IMAGE_API}/cinER0ESG0eJ49kXlExM0MEWGxW.jpg"))
    }

    private fun nestedScrollViewListener() {
        _binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            when (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                true -> getMovieFromApi(_pageCount)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

}