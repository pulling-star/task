package com.example.marvelapp.viemodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.db.Hero
import com.example.marvelapp.db.MarvelDatabase
import com.example.marvelapp.model.BaseModel
import com.example.marvelapp.model.BaseModelItem
import com.example.marvelapp.repository.HeroRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel(val heroRepo:HeroRepository):ViewModel() {

    lateinit var heroDetailsResponse : MutableLiveData<ArrayList<BaseModelItem>>
    var dbList = ArrayList<Hero>()
    var listFromDB = ArrayList<BaseModelItem>()

    init {
        redeclareLiveData()
    }

    private fun redeclareLiveData() {
        heroDetailsResponse = MutableLiveData<ArrayList<BaseModelItem>>()
    }

    fun getHeroList(context: Context) = viewModelScope.launch {
        try{
            val response = heroRepo.getHeroResults()
            insertDataIntoDB(response.body(),context)
            heroDetailsResponse.value = handleMovieResponse(response)
        }catch (e:Exception){
            Log.d("ViewModel",e.message.toString())
            e.printStackTrace()
        }
    }

    private fun insertDataIntoDB(response: BaseModel?, context: Context) {
        val heroDao = MarvelDatabase.getInstance(context).heroDao()

        if (response != null) {
            for(item in response){
                val hero = Hero()
                hero.name = item.name.toString()
                hero.realName = item.realname.toString()
                hero.bio = item.realname.toString()
                hero.team = item.team.toString()
                hero.firstAppearance = item.firstappearance.toString()
                hero.createdBy = item.createdby.toString()
                hero.imageUrl = item.imageurl.toString()
                dbList.add(hero)
            }
        }
        MarvelDatabase.databaseWriteExecutor.execute{
            heroDao.insert(dbList)
        }
    }

    public fun getDataFromDB(context: Context){
        val heroDao = MarvelDatabase.getInstance(context).heroDao()
        MarvelDatabase.databaseWriteExecutor.execute{
             val data = heroDao.getHero()
            for (item in data){
                val basemodel = BaseModelItem(item.bio,item.createdBy,item.firstAppearance,item.imageUrl,
                item.name,"",item.realName,item.team)
                listFromDB.add(basemodel)
            }

        }
        heroDetailsResponse.value = listFromDB
    }

    private fun handleMovieResponse(response: Response<BaseModel>): ArrayList<BaseModelItem>? {
        if(response.isSuccessful){
            response.body().let {
                return it!!
            }
        }else{
            Log.d("ViewModel","Error fetching response")
            return null
        }
    }
}