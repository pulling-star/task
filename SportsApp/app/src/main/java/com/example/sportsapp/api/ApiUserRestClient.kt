package com.example.sportsapp.api

import android.app.Activity
import com.example.sportsapp.model.BaseModel1
import com.example.sportsapp.model.TeamModel
import com.example.sportsapp.network.NetworkClient
import com.example.sportsapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient: Activity() {
    companion object {
        val instance = ApiUserRestClient()
    }

    var mApiUser: ApiUser? = null

    fun getTeamList(retrofitEventListener: RetrofitEventListener){
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getTeams()

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

    fun getTeamDetailList(teamId:String,retrofitEventListener: RetrofitEventListener){
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getTeamDetails(teamId)

        apiUserCall?.enqueue(object : Callback<BaseModel1> {

            override fun onResponse(
                call: Call<BaseModel1>?,
                response: Response<BaseModel1>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel1>, t: Throwable) {
                retrofitEventListener.onError(call, t)
            }
        })
    }

    fun getTeamSearchDetailList(teamName:String,retrofitEventListener: RetrofitEventListener){
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getTeamDetails(teamName)

        apiUserCall?.enqueue(object : Callback<BaseModel1> {

            override fun onResponse(
                call: Call<BaseModel1>?,
                response: Response<BaseModel1>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel1>, t: Throwable) {
                retrofitEventListener.onError(call, t)
            }
        })
    }
}