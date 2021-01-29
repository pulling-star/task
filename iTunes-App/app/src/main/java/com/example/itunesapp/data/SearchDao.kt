package com.example.itunesapp.data

import androidx.room.*

@Dao
interface SearchDao {

    @Query("SELECT * from searchData")
    fun getAll(): List<Search>

    @Query("SELECT * from searchData WHERE artistName LIKE :artistName ")
    fun get(artistName: String): List<Search>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(searchList: List<Search>?);

}