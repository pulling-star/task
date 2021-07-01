package com.example.marvelapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hero:ArrayList<Hero>)

    @Query("SELECT * from heroDetails")
    fun getHero() : List<Hero>
}