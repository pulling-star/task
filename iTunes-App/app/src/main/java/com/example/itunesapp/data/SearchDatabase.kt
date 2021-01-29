package com.example.itunesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Search::class], version = 1)
abstract class SearchDatabase:RoomDatabase() {
    abstract fun searchDao(): SearchDao

    companion object{
        lateinit var INSTANCE : SearchDatabase
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getInstance(context: Context): SearchDatabase {
            synchronized(SearchDatabase::class){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    SearchDatabase::class.java,
                    "my-search.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

}