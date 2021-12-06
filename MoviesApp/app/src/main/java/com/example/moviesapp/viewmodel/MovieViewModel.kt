package com.example.moviesapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.network.RetrofitClient
import com.example.moviesapp.utils.Constants
import retrofit2.Retrofit

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    fun getInvoice(invoiceBody: InvoiceBody): LiveData<ApiResult<*>> {
//        val mutableLiveData = MutableLiveData<ApiResult<*>>()
//        mutableLiveData.postValue(ApiResult.InProgress)
        try {
            viewModelScope.launch {
                withContext(IO) {
                    RetrofitClient.instance.getMovieResults(Constants.API_KEY,1).generateResponse(mutableLiveData, langResource)
                }
            }
        } catch (e: Exception) {
            Log.e("InvoiceData: ", e.message.orEmpty())
            mutableLiveData.postValue(ApiResult.Failure(e))
        }
        return mutableLiveData
    }
}