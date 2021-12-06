package com.example.moviesapp.network

import android.app.Activity
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserResClient :Activity(){

    companion object {
        val instance = ApiUserResClient()
    }

    val API_KEY = "0e12101a22c608993caa890e9dabea92"
    var mApiUser: Api? = null

    //    https://api.themoviedb.org/3/discover/movie?api_key=0e12101a22c608993caa890e9dabea92
    fun getMoviesList(page: Int, retrofitEventListener: RetrofitEventListener) {
        val retrofit = RetrofitClient.retrofitClient
        mApiUser = retrofit.create<Api>(Api::class.java)
        val apiUserCall = mApiUser?.getMovieResults(API_KEY, page)

        apiUserCall?.enqueue(object : Callback<BaseModel> {
            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response?.body())
                }
            }

            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }
}