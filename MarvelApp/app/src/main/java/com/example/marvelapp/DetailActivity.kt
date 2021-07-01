package com.example.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapp.databinding.ActivityDetailBinding
import com.example.marvelapp.model.BaseModelItem
import com.example.marvelapp.repository.HeroRepository
import com.example.marvelapp.viemodel.MainActivityViewModel
import com.example.marvelapp.viemodel.MainActivityViewModelFactory

class DetailActivity : AppCompatActivity() {
    companion object{
        const val TAG = "DetailActivity"
    }
    lateinit var binding: ActivityDetailBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = HeroRepository()
        mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(repository))
            .get(MainActivityViewModel::class.java)

        val bundle = intent.extras
        val data = bundle?.getParcelable<BaseModelItem>("data")
        Log.d(TAG,"data = ${data.toString()}")

        if(data == null){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }

        LoadingDataInUI(data)
    }

    private fun LoadingDataInUI(data: BaseModelItem?) {
        binding.apply {
            Glide.with(applicationContext)
                .load(data?.imageurl)
                .apply(RequestOptions().fitCenter())
                .into(heroImage)
            title.text = data?.name
            realName.text = data?.realname
            bio.text = data?.bio?.trim()
            team.text = data?.team
            firstappearance.text = data?.firstappearance
            createdby.text = data?.createdby
        }
    }
}