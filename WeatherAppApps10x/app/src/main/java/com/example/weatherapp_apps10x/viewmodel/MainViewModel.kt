package com.example.weatherapp_apps10x.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_apps10x.network.RetrofitClient
import com.example.weatherapp_apps10x.utils.ApiResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel:ViewModel() {

    fun getWeatherDetails(): LiveData<ApiResult<*>> {

        val mutableLiveData = MutableLiveData<ApiResult<*>>()
        mutableLiveData.postValue(ApiResult.InProgress)
        try {
            viewModelScope.launch {
                withContext(IO) {
                    val response = RetrofitClient().instance.getWeatherDetails("Bengaluru","9b8cb8c7f11c077f8c4e217974d9ee40")
                    val responseBody = response.body()
                    when {
                        response.isSuccessful -> {
                            when (responseBody != null) {
                                true -> {
                                    mutableLiveData.postValue(ApiResult.Success(responseBody))
                                }
                                else -> {
                                    mutableLiveData.postValue(ApiResult.NoData())
                                }
                            }
                        }
                        else -> mutableLiveData.postValue(ApiResult.NoData("Something went wrong! Try again later."))
                    }

                }
            }
        } catch (e: Exception) {
            Log.e("main :", e.message.orEmpty())
            mutableLiveData.postValue(ApiResult.Failure(e))
        }
        return mutableLiveData
    }

    fun getForecastDetails(): LiveData<ApiResult<*>> {

        val mutableLiveData = MutableLiveData<ApiResult<*>>()
        mutableLiveData.postValue(ApiResult.InProgress)
        try {
            viewModelScope.launch {
                withContext(IO) {
                    val response = RetrofitClient().instance.getForecastDetails("Bengaluru","9b8cb8c7f11c077f8c4e217974d9ee40")
                    val responseBody = response.body()
                    when {
                        response.isSuccessful -> {
                            when (responseBody != null) {
                                true -> {
                                    mutableLiveData.postValue(ApiResult.Success(responseBody))
                                }
                                else -> {
                                    mutableLiveData.postValue(ApiResult.NoData())
                                }
                            }
                        }
                        else -> mutableLiveData.postValue(ApiResult.NoData("Something went wrong! Try again later."))
                    }

                }
            }
        } catch (e: Exception) {
            Log.e("main :", e.message.orEmpty())
            mutableLiveData.postValue(ApiResult.Failure(e))
        }
        return mutableLiveData
    }
}