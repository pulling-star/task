package com.example.enrollmentapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsersEnrolled")
data class Users (

    @PrimaryKey
    @ColumnInfo(name = "firstName")
    var firstName:String = "",

    @ColumnInfo(name = "lastName")
    var lastName:String? = "",

    @ColumnInfo(name = "DOB")
    var dob:String = "",

    @ColumnInfo(name = "Gender")
    var gender:String = "",

    @ColumnInfo(name = "country")
    var country:String = "",

    @ColumnInfo(name = "state")
    var state:String = "",

    @ColumnInfo(name = "HomeTown")
    var homeTown:String = "",

    @ColumnInfo(name = "PhoneNumber")
    var phnNum:String = "",

    @ColumnInfo(name = "TelephoneNumber")
    var telNum:String? = ""

)