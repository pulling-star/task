package com.example.countryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryapp.model.BaseModel
import com.example.countryapp.repository.CountryRepo
import kotlinx.coroutines.launch
import retrofit2.Call

class MainActivityViewModel( private val countryRepo: CountryRepo):ViewModel() {
    val liveDataCountries = MutableLiveData<List<BaseModel>>()

    //this function launches coroutine on Main thread
    fun callCountriesApi() = viewModelScope.launch{
        val response = countryRepo.getCountries()
        liveDataCountries.value = response.body()

    }

    /*
    Within the coroutine, the call to countryRepo.getCountries()
    now suspends further execution of the coroutine until the withContext
    block in getCountries() finishes running.

    Once the withContext block finishes, the coroutine in callCountriesApi() resumes
    execution on the main thread with the result of the network request.
     */
}