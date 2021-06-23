package com.example.mapdemoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapdemoapp.model.BaseModel
import com.example.mapdemoapp.model.EndModel
import com.example.mapdemoapp.model.Response
import com.example.mapdemoapp.repository.MarkerRepo
import kotlinx.coroutines.launch

class MapViewModel(val markerRepo: MarkerRepo):ViewModel() {

    lateinit var markerResponse : MutableLiveData<ArrayList<EndModel>>
    var list = ArrayList<EndModel>()

    init {
        redeclareLiveData()
    }

    private fun redeclareLiveData() {
        markerResponse = MutableLiveData<ArrayList<EndModel>>()
    }

    fun getMarkerList() = viewModelScope.launch {
        try{
            val response = markerRepo.getMarkerList()
            val data = setDataInModel(response.body()?.response)
            markerResponse.value = data
        }catch (e:Exception){
            Log.d("ViewModel",e.message.toString())
            e.printStackTrace()
        }
    }

    private fun setDataInModel(data: Response?): ArrayList<EndModel> {
        if(data != null){
            val endModel1 = EndModel(data.`12-04-2021`.name,data.`12-04-2021`.latitude, data.`12-04-2021`.longitude)
            val endModel2 = EndModel(data.`13-04-2021`.name,data.`13-04-2021`.latitude, data.`13-04-2021`.longitude)
            val endModel3 = EndModel(data.`14-04-2021`.name,data.`14-04-2021`.latitude, data.`14-04-2021`.longitude)
            val endModel4 = EndModel(data.`16-04-2021`.name,data.`16-04-2021`.latitude, data.`16-04-2021`.longitude)
            val endModel5 = EndModel(data.`20-04-2021`.name,data.`20-04-2021`.latitude, data.`20-04-2021`.longitude)
            val endModel6 = EndModel(data.`22-04-2021`.name,data.`22-04-2021`.latitude, data.`22-04-2021`.longitude)
            val endModel7 = EndModel(data.`26-04-2021`.name,data.`26-04-2021`.latitude, data.`26-04-2021`.longitude)
            list = arrayListOf(endModel1,endModel2,endModel3,endModel4,endModel5,endModel6,endModel7)

        }
        return list
    }
}