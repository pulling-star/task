package com.example.marvelapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvelapp.adapter.HeroAdapter
import com.example.marvelapp.databinding.ActivityMainBinding
import com.example.marvelapp.model.BaseModel
import com.example.marvelapp.model.BaseModelItem
import com.example.marvelapp.repository.HeroRepository
import com.example.marvelapp.viemodel.MainActivityViewModel
import com.example.marvelapp.viemodel.MainActivityViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter:HeroAdapter
    var list = ArrayList<BaseModelItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = HeroRepository()
        mainActivityViewModel = ViewModelProvider(this,MainActivityViewModelFactory(repository))
                .get(MainActivityViewModel::class.java)
        setUpRecyclerView()

        getHeroListFromApi()

        binding.pullToRefresh.setOnRefreshListener(object:SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                list.shuffle()
                adapter.differ.submitList(list)
                adapter.notifyDataSetChanged()
                binding.pullToRefresh.isRefreshing = false
            }
        })
    }

    private fun getHeroListFromApi() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if(activeNetwork != null){
            mainActivityViewModel.getHeroList(this)
        } else{
            mainActivityViewModel.getDataFromDB(applicationContext)
        }

        mainActivityViewModel.heroDetailsResponse.observe(this, Observer {
            list = it
            adapter.differ.submitList(it)
        })
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        adapter = HeroAdapter(applicationContext,this,list)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.marvelCharRecyclerView.layoutManager = linearLayoutManager
        binding.marvelCharRecyclerView.adapter = adapter
    }

}