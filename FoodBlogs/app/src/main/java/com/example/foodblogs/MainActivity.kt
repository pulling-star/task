package com.example.foodblogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.foodblogs.databinding.ActivityMainBinding
import com.example.foodblogs.model.BaseResponse
import com.example.foodblogs.retrofit.ApiResult
import com.example.foodblogs.utils.Extensions.setImage
import com.example.foodblogs.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private lateinit var _mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _mainViewModel = MainViewModel()
        getFoodDetailsFromApi()
    }

    private fun getFoodDetailsFromApi() {
        _mainViewModel.getFoodDetails().observe(this,{
            when(it){
                is ApiResult.Success -> {
                    when(it.data){
                        is BaseResponse -> {
                            val data = it.data.data
                            Log.d("mainAct",data.toString())
                            _binding.apply {
                                tvCardHeading2.text = data.card_details.title +" " + data.card_details.city
                                tvFoodOneTitle.text = data.card[0].title
                                tvFoodOneDescription.text = data.card[0].desc
                                ivFoodOne.setImage(data.card[0].img)
                                tvFoodTwoTitle.text = data.card[1].title
                                tvFoodTwoDescription.text = data.card[1].desc
                                ivFoodTwo.setImage(data.card[1].img)
                            }
                        }
                    }
                }
                is ApiResult.Failure -> {

                }
                is ApiResult.NoData -> {

                }
                is ApiResult.InProgress -> {

                }
            }
        })
    }
}