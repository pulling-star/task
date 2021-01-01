package com.example.itunesapp.api

import android.app.Activity
import com.example.itunesapp.model.SearchResultModel
import com.example.itunesapp.network.NetworkClient
import com.example.itunesapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient : Activity() {
    companion object {
        val instance = ApiUserRestClient()
    }

    var mApiUser: APIUser? = null

    // https://itunes.apple.com/search? term=#searchTerm

    fun getSearchList(searchText: String, retrofitEventListener: RetrofitEventListener) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<APIUser>(APIUser::class.java)

        val data = HashMap<String, String>()
        data["term"] = searchText

        val apiUserCall = mApiUser!!.getSearchResults(data)

        apiUserCall.enqueue(object : Callback<SearchResultModel> {

            override fun onResponse(
                call: Call<SearchResultModel>?,
                response: Response<SearchResultModel>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response?.body())
                }
            }

            override fun onFailure(call: Call<SearchResultModel>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })


    }
}