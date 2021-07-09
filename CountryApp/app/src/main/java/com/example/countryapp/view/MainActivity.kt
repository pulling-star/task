package com.example.countryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.model.BaseModel
import com.example.countryapp.repository.CountryRepo
import com.example.countryapp.viewmodel.MainActivityViewModel
import com.example.countryapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var countryRecyclerView:RecyclerView
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var indeterminateBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = CountryRepo()
        mainActivityViewModel = ViewModelProvider(this,ViewModelFactory(repo))
            .get(MainActivityViewModel::class.java)
        bindViews()
        mainActivityViewModel.callCountriesApi()
        mainActivityViewModel.liveDataCountries.observe(this, Observer {
            indeterminateBar.visibility = View.GONE
            setRecyclerView(it)
        })
    }

    private fun bindViews() {
        countryRecyclerView = findViewById(R.id.countryRecyclerView)
        indeterminateBar = findViewById(R.id.indeterminateBar)
    }

    private fun setRecyclerView(list: List<BaseModel>) {
        val mAdapter = CountryAdapter(this, list)
        val mLayoutManager = LinearLayoutManager(this)
        countryRecyclerView.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        countryRecyclerView.itemAnimator = DefaultItemAnimator()
        countryRecyclerView.adapter = mAdapter
    }
}