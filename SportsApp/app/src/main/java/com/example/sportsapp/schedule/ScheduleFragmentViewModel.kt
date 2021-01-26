package com.example.sportsapp.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportsapp.api.ApiUserRestClient
import com.example.sportsapp.model.BaseModel2
import com.example.sportsapp.model.ScheduleModel
import com.example.sportsapp.network.RetrofitEventListener
import retrofit2.Call


class ScheduleFragmentViewModel: ViewModel() {
    val liveDataTeamSchedule = MutableLiveData<List<ScheduleModel>>()

    fun CallTeamEventList(teamId: String) {
        ApiUserRestClient.instance.getTeamEventScheduleList(teamId ,object : RetrofitEventListener {
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel2){
                    liveDataTeamSchedule.value = response.results
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {

            }

        })
    }
}