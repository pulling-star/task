package com.example.newsapp.api

import android.app.Activity
import com.example.newsapp.model.BaseModel
import com.example.newsapp.network.NetworkClient
import com.example.newsapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiUserRestClient: Activity() {

    companion object {
        val instance = ApiUserRestClient()
        val apikey = "a1f616c6388a4a74ad396c4e42caaff8"
    }
    var apiUser:ApiUser? = null

    fun getNews(retrofitEventListener: RetrofitEventListener){
        val retrofit = NetworkClient.retrofitClient
        apiUser = retrofit.create(ApiUser::class.java)
        val apiUserCall = apiUser?.getNews("india", apikey)
        apiUserCall?.enqueue(object : Callback<BaseModel> {
            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel>, t: Throwable) {
                retrofitEventListener.onError(call,t)
            }

        })
    }

}