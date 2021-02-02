package com.example.countryapp.api


import com.example.countryapp.model.BaseModel
import com.example.countryapp.network.NetworkClient
import com.example.countryapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient {
    companion object {
        val instance = ApiUserRestClient()
    }
    var mApiUser: ApiUser? = null

    fun getCountriesList(retrofitEventListener: RetrofitEventListener){
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)
        val apiUserCall = mApiUser?.getCountries()
        apiUserCall?.enqueue(object : Callback<List<BaseModel>> {

            override fun onResponse(
                call: Call<List<BaseModel>>?,
                response: Response<List<BaseModel>>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<List<BaseModel>>?, t: Throwable?) {
                retrofitEventListener.onError(call, t)
            }
        })
    }
}