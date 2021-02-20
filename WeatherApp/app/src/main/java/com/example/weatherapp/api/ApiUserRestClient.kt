package com.example.weatherapp.api

import android.app.Activity
import android.util.Log
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.BaseModel2
import com.example.weatherapp.network.NetworkClient
import com.example.weatherapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient : Activity() {

    companion object {
        val instance = ApiUserRestClient()
    }

    var mApiUser: ApiUser? = null

    fun getWeatherDetails(lat: String, lon: String, retrofitEventListener: RetrofitEventListener) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall =
            mApiUser?.getWeather(lat, lon, "minutely", "c8388944e2520aa17b43da2e294ed346")
        Log.d("ApiCLient", "$apiUserCall")
        Log.d("ApiCLient", "$lat,$lon")
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

    fun getWeatherDetailsLoc(q: String, retrofitEventListener: RetrofitEventListener) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getWeatherInLoc(q, "c8388944e2520aa17b43da2e294ed346")
        Log.d("ApiCLient", "$apiUserCall")
        Log.d("ApiCLient", q)
        apiUserCall?.enqueue(object : Callback<BaseModel2> {

            override fun onResponse(
                call: Call<BaseModel2>?,
                response: Response<BaseModel2>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel2>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }
}