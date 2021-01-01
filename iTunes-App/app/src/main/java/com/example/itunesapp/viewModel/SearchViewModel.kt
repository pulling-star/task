package com.example.itunesapp.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itunesapp.api.ApiUserRestClient
import com.example.itunesapp.data.Search
import com.example.itunesapp.data.SearchDatabase
import com.example.itunesapp.model.ResultModel
import com.example.itunesapp.model.SearchResultModel
import com.example.itunesapp.network.RetrofitEventListener
import retrofit2.Call


class SearchViewModel() : ViewModel() {
    val liveDataSearch = MutableLiveData<List<ResultModel>>()

    fun callSearchList(context: Context, searchtext: String) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        @Suppress("DEPRECATION") val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork != null) {
            ApiUserRestClient.instance.getSearchList(searchtext, object : RetrofitEventListener {
                override fun onSuccess(call: Call<*>?, response: Any?) {
                    if (response is SearchResultModel) {
                        liveDataSearch.value = response?.resultModels
                        var dbList: MutableList<Search> = mutableListOf()
                        for (index in response.resultModels!!) {
                            if (index.getTrackId() != null && index.getTrackName() != null && index.getArtistName() != null && index.getArtworkUrl100() != null) {
                                var search = Search(
                                    musicId = index.getTrackId(),
                                    musicTitle = index.getTrackName()!!,
                                    artistName = index.getArtistName()!!,
                                    imagePath = index.getArtworkUrl100()!!
                                )
                                if (search != null) {
                                    dbList?.add(search)
                                }
                            }
                        }
                        val searchDao = SearchDatabase.getInstance(context).searchDao()
                        SearchDatabase.databaseWriteExecutor.execute {
                            searchDao.insertAll(dbList)
                        }
                    }
                }

                override fun onError(call: Call<*>?, t: Throwable?) {
                }
            })
        } else {
            val searchDao = SearchDatabase.getInstance(context).searchDao()
            val dbList = searchDao.get(searchtext)
            val dbNewList: MutableList<ResultModel> = mutableListOf()

            for (index in dbList) {
                val resultModel = ResultModel()
                resultModel.setTrackId(index.musicId)
                resultModel.setTrackName(index.musicTitle)
                resultModel.setArtistName(index.artistName)
                resultModel.setArtworkUrl100(index.imagePath)
                dbNewList.add(resultModel)
            }
            liveDataSearch.value = dbNewList
        }
    }
}

