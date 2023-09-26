package com.example.contentlistapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.contentlistapp.models.Content
import com.example.contentlistapp.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val contentRepository: ContentRepository) :ViewModel() {

    fun getPageData(pageNumber:Int):List<Content>{
        return contentRepository.getDataFromJSONAsset(pageNumber)
    }
}