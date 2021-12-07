package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ActivityMovieDetailBinding
import com.example.moviesapp.model.ResultModel
import com.example.moviesapp.utils.Constants

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val movieData = intent.getSerializableExtra("ResultData") as ResultModel
        setUpUI(movieData)
    }

    private fun setUpUI(movie: ResultModel) {
        _binding.tvMovieTitle.text = movie.originalTitle
        _binding.tvDate.text = movie.releaseDate
        _binding.tvRating.text = movie.voteAverage.toString()
        _binding.tvVotedUsers.text = movie.voteCount.toString()
        _binding.ratingBar.rating = movie.voteAverage.toFloat() / 2
        _binding.tvMovieDesc.text = movie.overview
        _binding.tvAdult.text = when (movie.adult) {
            true -> "A"
            else -> "U+"
        }
        Glide.with(this)
            .load("${Constants.IMAGE_API}${movie.posterPath}")
            .into(_binding.ivMovieImage)
    }
}