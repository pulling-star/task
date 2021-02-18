package com.example.enrollmentapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Users::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao

    companion object {
        @Volatile
        private var userDbInstance: UsersDatabase? = null

        private const val NUMBER_OF_THREADS = 2
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getInstance(context: Context): UsersDatabase {
            synchronized(this) {
                var instance = userDbInstance

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UsersDatabase::class.java, "users_enrolled"
                    )
                        .build()
                    userDbInstance = instance
                }
                return instance
            }
        }

    }
}