package com.example.itunesapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultModel {

    @SerializedName("trackId")
    @Expose
    private var trackId: Long? = null

    @SerializedName("artistName")
    @Expose
    private var artistName: String? = null

    @SerializedName("trackName")
    @Expose
    private var trackName: String? = null

    @SerializedName("artworkUrl100")
    @Expose
    private var artworkUrl100: String? = null

    fun getTrackId(): Long? {
        return trackId
    }

    fun setTrackId(trackId:Long?){
        this.trackId = trackId
    }

    fun getArtistName(): String? {
        return artistName
    }

    fun setArtistName(artistName: String?) {
        this.artistName = artistName
    }

    fun getTrackName(): String? {
        return trackName
    }

    fun setTrackName(trackName: String?) {
        this.trackName = trackName
    }

    fun getArtworkUrl100(): String? {
        return artworkUrl100
    }

    fun setArtworkUrl100(artworkUrl100: String?) {
        this.artworkUrl100 = artworkUrl100
    }
}