package com.example.countryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.repository.CountryRepo

class ViewModelFactory (val countryRepo: CountryRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(countryRepo) as T
    }

}