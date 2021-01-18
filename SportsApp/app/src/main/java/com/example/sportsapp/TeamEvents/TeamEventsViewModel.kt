package com.example.sportsapp.TeamEvents

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportsapp.api.ApiUserRestClient
import com.example.sportsapp.model.BaseModel1
import com.example.sportsapp.model.TeamModel
import com.example.sportsapp.network.RetrofitEventListener
import retrofit2.Call

class TeamEventsViewModel:ViewModel() {
    val liveDataTeamDetailSearch = MutableLiveData<List<TeamModel>>()


    fun CallTeamDetailList(teamId:String){
        ApiUserRestClient.instance.getTeamDetailList(teamId,object : RetrofitEventListener {
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel1){
                    liveDataTeamDetailSearch.value = response.teams
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {

            }

        })
    }

    fun CallTeamSearchDetailList(teamName:String){
        ApiUserRestClient.instance.getTeamSearchDetailList(teamName,object : RetrofitEventListener {
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel1){
                    Log.d("TeamEventsViewModel","${response.teams}")
                    liveDataTeamDetailSearch.value = response.teams

                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {

            }

        })
    }
}