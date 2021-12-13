package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.helpers.Result
import com.example.moviesapp.model.BaseModel
import com.example.moviesapp.network.RetrofitClient
import com.example.moviesapp.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    fun getMoviesList(pageCount: Int): LiveData<BaseModel> {
        val mutableLiveData = MutableLiveData<BaseModel>()
        viewModelScope.launch {
            withContext(IO) {
                val response = RetrofitClient.instance.getMovieResults(Constants.API_KEY, pageCount)
                when(response) {
                    is Result.Success<*> -> mutableLiveData.postValue(response.body())
                    else -> {//error in UI
                    }
//                    is Result.response.isSuccessful -> mutableLiveData.postValue(response.body())
                }
            }
        }
        return mutableLiveData
    }
}