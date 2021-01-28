package com.example.weatherapp

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.ApiUserRestClient
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.network.RetrofitEventListener
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call

class MainActivityViewModel:ViewModel() {
    val liveDataWeather = MutableLiveData<BaseModel>()

    fun CallWeatherApi(){
        ApiUserRestClient.instance.getWeatherDetails(object:RetrofitEventListener{
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel){
                    liveDataWeather.value = response.copy()
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {

            }

        })
    }

}