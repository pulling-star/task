package com.example.marvelapp.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelapp.repository.HeroRepository

class MainActivityViewModelFactory(val heroRepository: HeroRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(heroRepository) as T
    }

}