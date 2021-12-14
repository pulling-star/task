package com.example.foodblogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.foodblogs.adapter.SliderAdapter
import com.example.foodblogs.databinding.ActivityMainBinding
import com.example.foodblogs.model.BaseResponse
import com.example.foodblogs.model.Card
import com.example.foodblogs.retrofit.ApiResult
import com.example.foodblogs.utils.Extensions
import com.example.foodblogs.utils.Extensions.setImage
import com.example.foodblogs.utils.ProgressUtils
import com.example.foodblogs.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding : ActivityMainBinding
    private lateinit var _mainViewModel:MainViewModel
    private lateinit var _sliderAdapter:SliderAdapter

    private val _detailBlogBehaviour: BottomSheetBehavior<View> by lazy { BottomSheetBehavior.from(_binding.bottomSheetBlogDetail.bmRootCoordinator as View) }

    private val sliderImageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _mainViewModel = MainViewModel()
        _sliderAdapter = SliderAdapter()
        _binding.bottomSheetBlogDetail.viewPager.adapter = _sliderAdapter
        setOnClickListeners()
        getFoodDetailsFromApi()
    }

    private fun setOnClickListeners() {
        _binding.cvDashboard.setOnClickListener(this)
    }

    private fun getFoodDetailsFromApi() {
        _mainViewModel.getFoodDetails().observe(this,{
            when(it){
                is ApiResult.Success -> {
                    ProgressUtils.closeProgressIndicator()
                    when(it.data){
                        is BaseResponse -> {
                            val data = it.data.data
                            Log.d("mainAct",data.toString())
                            val sortedCardList = sortByCardNo(data.card)
                            _binding.apply {
                                tvCardHeading2.text = data.card_details.title +" " + data.card_details.city
                                tvFoodOneTitle.text = sortedCardList[0].title
                                tvFoodOneDescription.text = sortedCardList[0].desc
                                ivFoodOne.setImage(sortedCardList[0].img)
                                tvFoodTwoTitle.text = sortedCardList[1].title
                                tvFoodTwoDescription.text = sortedCardList[1].desc
                                ivFoodTwo.setImage(sortedCardList[1].img)
                                for(item in data.card){ sliderImageList.add(item.img) }
                            }
                        }
                    }
                }
                is ApiResult.Failure -> {
                    Extensions.errorShortSnackBar(_binding.root,getString(R.string.failure_error))
                    ProgressUtils.closeProgressIndicator()
                }
                is ApiResult.NoData -> ProgressUtils.closeProgressIndicator()
                is ApiResult.InProgress -> ProgressUtils.showProgressIndicator(this)
            }
        })
    }

    private fun sortByCardNo(list: List<Card>) : List<Card>{
        val sortedList = list.map { it.copy() }.toMutableList()
        sortedList.sortBy { it.card_no }
        return sortedList
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.cv_dashboard -> {
                _detailBlogBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                setUpAdapter()
            }
        }
    }

    private fun setUpAdapter() {
        _sliderAdapter.updateList(sliderImageList)
    }
}