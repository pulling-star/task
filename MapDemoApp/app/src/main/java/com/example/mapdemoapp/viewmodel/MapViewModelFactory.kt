package com.example.mapdemoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mapdemoapp.repository.MarkerRepo

class MapViewModelFactory (val markerRepo: MarkerRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MapViewModel(markerRepo) as T
    }

}