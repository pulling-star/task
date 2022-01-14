package com.example.weatherapp_apps10x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.weatherapp_apps10x.adapter.ForecastAdapter
import com.example.weatherapp_apps10x.databinding.ActivityMainBinding
import com.example.weatherapp_apps10x.model.ForecastModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.util.DisplayMetrics
import com.example.weatherapp_apps10x.model.ForecastResponse
import com.example.weatherapp_apps10x.model.ListA
import com.example.weatherapp_apps10x.model.WeatherResponse
import com.example.weatherapp_apps10x.utils.ApiResult
import com.example.weatherapp_apps10x.utils.Extensions.convertKelvinToCelsius
import com.example.weatherapp_apps10x.utils.Extensions.getDayFromDate
import com.example.weatherapp_apps10x.utils.ProgressUtils
import com.example.weatherapp_apps10x.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var _binding:ActivityMainBinding
    private lateinit var _forecastAdapter:ForecastAdapter
    private lateinit var _mainViewModel:MainViewModel

    private val _forecastBehaviour: BottomSheetBehavior<View> by lazy { BottomSheetBehavior.from(_binding.bmSheetForecast.rootCoordinator as View) }

    var arrList = ArrayList<ForecastModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _mainViewModel = MainViewModel()
        _forecastAdapter = ForecastAdapter()
        _binding.bmSheetForecast.rcvForecast.adapter = _forecastAdapter
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        _forecastBehaviour.maxHeight = height/2
        getWeatherDetails()
        getForecastDetails()
        val l1 = ForecastModel("Monday","23")
        val l2 = ForecastModel("Tuesday","25")
        val l3 = ForecastModel("Friday","28")
        val l4 = ForecastModel("Monday","23")
        val l5 = ForecastModel("Friday","28")
        val l6 = ForecastModel("Monday","23")
        val list = listOf<ForecastModel>(l1,l2,l3,l4,l5,l6)
        _forecastAdapter.updateList(list)
        _forecastBehaviour.isDraggable = false

    }

    private fun getForecastDetails() {
        _mainViewModel.getForecastDetails().observe(this,{
            when(it){
                is ApiResult.Success -> {
                    ProgressUtils.closeProgressIndicator()
                    when(it.data){
                        is ForecastResponse -> {
                            val data = it.data
                            formatDataList(it.data.list)
                            this.getDayFromDate(data.list[0].dt_txt)
                        }
                    }
                }
                is ApiResult.Failure -> {}
                is ApiResult.NoData -> {}
                is ApiResult.InProgress -> ProgressUtils.showProgressIndicator(this)
            }
        })
    }

    private fun formatDataList(list: List<ListA>) {
       
    }

    private fun getWeatherDetails() {
        _mainViewModel.getWeatherDetails().observe(this,{
            when(it){
                is ApiResult.Success -> {
                    ProgressUtils.closeProgressIndicator()
                    when(it.data){
                        is WeatherResponse -> {
                            val data = it.data
                            _binding.tvTemperature.text = this.convertKelvinToCelsius(data.main.temp)
                            _binding.tvLocation.text = data.name
                            Handler(Looper.getMainLooper()).postDelayed({
                                _forecastBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                            }, 1000)
                        }
                    }
                }
                is ApiResult.Failure -> {}
                is ApiResult.NoData -> {}
                is ApiResult.InProgress -> ProgressUtils.showProgressIndicator(this)
            }
        })
    }
}