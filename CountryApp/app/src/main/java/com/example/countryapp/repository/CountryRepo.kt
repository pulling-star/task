package com.example.countryapp.repository

import com.example.countryapp.model.BaseModel
import com.example.countryapp.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class CountryRepo {

    suspend fun getCountries(): Response<List<BaseModel>> {

        // Move the execution of the coroutine to the I/O dispatcher
        return withContext(Dispatchers.IO){
            //Blocking network request code
            Retrofit.api.getCountries()
        }
    }
}