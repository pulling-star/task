package com.example.weatherapp.api

import android.app.Activity
import android.util.Log
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.network.NetworkClient
import com.example.weatherapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient:Activity() {

    companion object {
        val instance = ApiUserRestClient()
    }

    var mApiUser: ApiUser? = null

    fun getWeatherDetails(retrofitEventListener: RetrofitEventListener){
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getWeather()
        Log.d("MainActivity","$apiUserCall")
        apiUserCall?.enqueue(object : Callback<BaseModel> {

            override fun onResponse(
                call: Call<BaseModel>?,
                response: Response<BaseModel>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }
}