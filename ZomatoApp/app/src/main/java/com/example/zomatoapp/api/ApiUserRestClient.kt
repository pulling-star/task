package com.example.zomatoapp.api

import android.app.Activity
import android.util.Log
import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.network.NetworkClient
import com.example.zomatoapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient : Activity() {
    companion object {
        val instance = ApiUserRestClient()
    }

    var mApiUser: ApiUser? = null

    fun getRestaurantDetails(query: String, retrofitEventListener: RetrofitEventListener) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getRestaurants("11332", "city", query)
        Log.d("ApiUserRestClient", "$apiUserCall")
        apiUserCall?.enqueue(object : Callback<BaseModel1> {

            override fun onResponse(
                call: Call<BaseModel1>?,
                response: Response<BaseModel1>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel1>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }
}