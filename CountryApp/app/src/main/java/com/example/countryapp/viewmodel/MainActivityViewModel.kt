package com.example.countryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.api.ApiUserRestClient
import com.example.countryapp.model.BaseModel
import com.example.countryapp.network.RetrofitEventListener
import retrofit2.Call

class MainActivityViewModel:ViewModel() {
    val liveDataCountries = MutableLiveData<List<BaseModel>>()

    fun callCountriesApi(){
        ApiUserRestClient.instance.getCountriesList(object : RetrofitEventListener {
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is List<*>){
                    liveDataCountries.value = response as List<BaseModel>?
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {
                Log.d("MainActivityViewModel","Error")
            }

        })
    }
}