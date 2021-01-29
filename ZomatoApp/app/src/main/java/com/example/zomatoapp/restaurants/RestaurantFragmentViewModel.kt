package com.example.zomatoapp.restaurants

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zomatoapp.api.ApiUserRestClient
import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.network.RetrofitEventListener
import retrofit2.Call

class RestaurantFragmentViewModel:ViewModel() {
    val livedataRestaurantSearch= MutableLiveData<BaseModel1>()

    fun CallSearchRestaurantApi(query:String){
        ApiUserRestClient.instance.getRestaurantDetails(query,object : RetrofitEventListener {
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if(response is BaseModel1){
                    Log.d(RestaurantFragment.TAG,"response= $response")
                    livedataRestaurantSearch.value = response
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {
                Log.d(RestaurantFragment.TAG,"Error")
            }

        })

    }
}