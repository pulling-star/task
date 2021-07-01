package com.example.marvelapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Hero::class], version = 1)
abstract class MarvelDatabase:RoomDatabase() {
    abstract fun heroDao(): HeroDao

    companion object{
        @Volatile
        private var marvelDbInstance :MarvelDatabase? =null

        private val NUMBER_OF_THREADS = 2
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getInstance(context: Context): MarvelDatabase {
            synchronized(this) {
                var instance = marvelDbInstance

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        MarvelDatabase::class.java, "my_hero")
                        .build()
                    marvelDbInstance = instance
                }
                return instance
            }
        }
    }
}