package com.example.weatherapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.ApiUserRestClient
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.BaseModel2
import com.example.weatherapp.network.RetrofitEventListener
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call

class MainActivityViewModel:ViewModel() {
    val liveDataWeather = MutableLiveData<BaseModel>()
    val liveDataWeatherLoc = MutableLiveData<BaseModel2>()

    fun CallWeatherApi(lat:String,lon:String){
        ApiUserRestClient.instance.getWeatherDetails(lat,lon,object:RetrofitEventListener{
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel){
                    liveDataWeather.value = response.copy()
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {
                    Log.d("ViewModel","Error")
            }

        })
    }

    fun CallWeatherApiLoc(q:String){
        ApiUserRestClient.instance.getWeatherDetailsLoc(q,object:RetrofitEventListener{
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel2){
                    liveDataWeatherLoc.value = response.copy()
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {
                Log.d("ViewModel","Error")
            }

        })
    }

}