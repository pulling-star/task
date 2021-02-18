package com.example.enrollmentapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:Users)

    @Query("SELECT * from UsersEnrolled")
    fun getUsers():List<Users>

}