package com.example.marvelapp.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroDetails")

data class Hero (
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name :String = "",

    @ColumnInfo(name = "realName")
    var realName:String ="",

    @ColumnInfo(name = "team")
    var team :String = "",

    @ColumnInfo(name = "firstAppearance")
    var firstAppearance :String = "",

    @ColumnInfo(name = "createdBy")
    var createdBy:String ="",

    @ColumnInfo(name = "imageUrl")
    var imageUrl :String = "",

    @ColumnInfo(name = "bio")
    var bio :String = ""
        )