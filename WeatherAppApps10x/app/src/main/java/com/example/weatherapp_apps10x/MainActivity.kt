package com.example.weatherapp_apps10x

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.weatherapp_apps10x.model.ForecastResponse
import com.example.weatherapp_apps10x.model.ListA
import com.example.weatherapp_apps10x.model.WeatherResponse
import com.example.weatherapp_apps10x.utils.ApiResult
import com.example.weatherapp_apps10x.utils.Extensions.convertKelvinToCelsius
import com.example.weatherapp_apps10x.utils.Extensions.getDayFromDate
import com.example.weatherapp_apps10x.utils.ProgressUtils
import com.example.weatherapp_apps10x.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _forecastAdapter: ForecastAdapter
    private lateinit var _mainViewModel: MainViewModel

    private val _forecastBehaviour: BottomSheetBehavior<View> by lazy {
        BottomSheetBehavior.from(
            _binding.bmSheetForecast.rootCoordinator as View
        )
    }

    private var arrList = ArrayList<ForecastModel>()
    private val arrPairs = ArrayList<Pair<String, Double>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setUpData()
        clickListeners()
        checkNetworkConnection()
    }

    private fun setUpData() {
        _mainViewModel = MainViewModel()
        _forecastAdapter = ForecastAdapter()
        _binding.bmSheetForecast.rcvForecast.adapter = _forecastAdapter

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        _forecastBehaviour.maxHeight = height / 2
        _forecastBehaviour.isDraggable = false
    }

    private fun clickListeners() {
        _binding.btnRetry.setOnClickListener(this)
    }

    private fun checkNetworkConnection() {
        ProgressUtils.showProgressIndicator(this)
        when (checkForInternet(this)) {
            true -> {
                _binding.tvErrorNote.visibility = View.GONE
                _binding.btnRetry.visibility = View.GONE
                _binding.tvTemperature.visibility = View.VISIBLE
                _binding.tvLocation.visibility = View.VISIBLE
                _binding.clMain.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.white
                    )
                )
                getWeatherDetails()
                getForecastDetails()
            }
            else -> {
                _binding.tvErrorNote.visibility = View.VISIBLE
                _binding.btnRetry.visibility = View.VISIBLE
                _binding.tvTemperature.visibility = View.GONE
                _binding.tvLocation.visibility = View.GONE
                _forecastBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
                _binding.clMain.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.holo_red_light
                    )
                )
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            ProgressUtils.closeProgressIndicator()
        }, 1000)
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun getWeatherDetails() {
        _mainViewModel.getWeatherDetails().observe(this, {
            when (it) {
                is ApiResult.Success -> {
                    ProgressUtils.closeProgressIndicator()
                    when (it.data) {
                        is WeatherResponse -> {
                            val data = it.data
                            _binding.tvTemperature.text =
                                this.convertKelvinToCelsius(data.main.temp)
                            _binding.tvLocation.text = data.name
                            Handler(Looper.getMainLooper()).postDelayed({
                                _forecastBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                            }, 1000)
                        }
                    }
                }
                is ApiResult.Failure -> {
                    _binding.tvErrorNote.visibility = View.VISIBLE
                    _binding.btnRetry.visibility = View.VISIBLE
                    _binding.clMain.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            android.R.color.holo_red_light
                        )
                    )
                }
                is ApiResult.NoData -> {
                }
                is ApiResult.InProgress -> ProgressUtils.showProgressIndicator(this)
            }
        })
    }

    private fun getForecastDetails() {
        _mainViewModel.getForecastDetails().observe(this, {
            when (it) {
                is ApiResult.Success -> {
                    ProgressUtils.closeProgressIndicator()
                    when (it.data) {
                        is ForecastResponse -> {
                            val data = it.data
                            for (item in data.list) {
                                arrPairs.add(Pair(getDayFromDate(item.dt_txt), item.main.temp))
                            }
                            _forecastAdapter.updateList(formatDataList(arrPairs))
                        }
                    }
                }
                is ApiResult.Failure -> {
                }
                is ApiResult.NoData -> {
                }
                is ApiResult.InProgress -> ProgressUtils.showProgressIndicator(this)
            }
        })
    }

    private fun formatDataList(list: List<Pair<String, Double>>): ArrayList<ForecastModel> {
        var sum = 0.0
        var count = 0
        for (i in list.indices) {
            when (i) {
                0 -> {
                    sum = list[i].second
                    count++
                }
                else -> {
                    if (list[i].first == list[i - 1].first) {
                        sum += list[i].second
                        count++
                    } else {
                        sum /= count
                        sum -= 273
                        arrList.add(ForecastModel(list[i].first, sum.toInt().toString()))
                        sum = list[i].second
                        count = 1
                    }
                }
            }
        }
        return arrList
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_retry -> {
                checkNetworkConnection()
            }
        }
    }
}