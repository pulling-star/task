package com.example.sportsapp.sportsteam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportsapp.api.ApiUserRestClient
import com.example.sportsapp.model.BaseModel1
import com.example.sportsapp.model.TeamModel
import com.example.sportsapp.network.RetrofitEventListener
import retrofit2.Call

class TeamFragmentViewModel: ViewModel() {
    val liveDataTeamSearch = MutableLiveData<List<TeamModel>>()

    fun CallTeamList(){
        ApiUserRestClient.instance.getTeamList(object : RetrofitEventListener{
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel1){
                    liveDataTeamSearch.value = response.teams
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {

            }

        })
    }
}