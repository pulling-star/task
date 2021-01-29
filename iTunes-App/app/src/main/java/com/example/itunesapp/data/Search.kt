package com.example.itunesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchData")
data class Search(
    @PrimaryKey()
    @ColumnInfo(name = "musicId")
    var musicId: Long? = null,
    @ColumnInfo(name = "musicTitle")
    var musicTitle: String = "",
    @ColumnInfo(name = "artistName")
    var artistName: String = "",
    @ColumnInfo(name = "imagePath")
    var imagePath: String = "",

    )