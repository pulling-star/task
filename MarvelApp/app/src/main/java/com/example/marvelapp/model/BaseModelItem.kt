package com.example.marvelapp.model

import android.os.Parcel
import android.os.Parcelable

data class BaseModelItem(
    val bio: String?,
    val createdby: String?,
    val firstappearance: String?,
    val imageurl: String?,
    val name: String?,
    val publisher: String?,
    val realname: String?,
    val team: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bio)
        parcel.writeString(createdby)
        parcel.writeString(firstappearance)
        parcel.writeString(imageurl)
        parcel.writeString(name)
        parcel.writeString(publisher)
        parcel.writeString(realname)
        parcel.writeString(team)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseModelItem> {
        override fun createFromParcel(parcel: Parcel): BaseModelItem {
            return BaseModelItem(parcel)
        }

        override fun newArray(size: Int): Array<BaseModelItem?> {
            return arrayOfNulls(size)
        }
    }
}