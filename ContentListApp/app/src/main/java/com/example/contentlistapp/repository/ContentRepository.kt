package com.example.contentlistapp.repository

import android.app.Application
import android.content.Context
import com.example.contentlistapp.MainApplication
import com.example.contentlistapp.models.Content
import com.example.contentlistapp.models.ContentListModel
import com.example.contentlistapp.utils.ReadJSONFromAssets
import com.example.contentlistapp.utils.Utility
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentRepository @Inject constructor(val application: Application) {

    //Reading data from assets folder
    fun getDataFromJSONAsset(pageNumber:Int):List<Content>{
        val data = ReadJSONFromAssets(application.baseContext ,Utility.getPageData(pageNumber))
        val gson = Gson()
        val model = gson.fromJson(data,ContentListModel::class.java)
        return model.page.contentItems.content
    }
}