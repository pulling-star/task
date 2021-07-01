package com.example.marvelapp.repository

import com.example.marvelapp.network.Retrofit

class HeroRepository {

    suspend fun getHeroResults() = Retrofit.api.getHeroResults()

}