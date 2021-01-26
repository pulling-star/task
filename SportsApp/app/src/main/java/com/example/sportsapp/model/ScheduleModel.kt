package com.example.sportsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleModel(
        @SerializedName("idEvent")
        @Expose
        val idEvent:String,
        @SerializedName("idSoccerXML")
        @Expose
        val idSoccerXML:String?,
        @SerializedName("idAPIfootball")
        @Expose
        val idAPIfootball:String,
        @SerializedName("strEvent")
        @Expose
        val strEvent:String,
        @SerializedName("strEventAlternate")
        @Expose
        val strEventAlternate:String,
        @SerializedName("strFilename")
        @Expose
        val strFilename:String,
        @SerializedName("strSport")
        @Expose
        val strSport:String,
        @SerializedName("idLeague")
        @Expose
        val idLeague:String,
        @SerializedName("strLeague")
        @Expose
        val strLeague:String,
        @SerializedName("strSeason")
        @Expose
        val strSeason:String,
        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN:String,
        @SerializedName("strHomeTeam")
        @Expose
        val strHomeTeam:String,
        @SerializedName("strAwayTeam")
        @Expose
        val strAwayTeam:String,
        @SerializedName("intHomeScore")
        @Expose
        val intHomeScore:String,
        @SerializedName("intRound")
        @Expose
        val intRound:String,
        @SerializedName("intAwayScore")
        @Expose
        val intAwayScore:String,
        @SerializedName("intSpectators")
        @Expose
        val intSpectators:String?,
        @SerializedName("strOfficial")
        @Expose
        val strOfficial:String,
        @SerializedName("strHomeGoalDetails")
        @Expose
        val strHomeGoalDetails:String,
        @SerializedName("strHomeRedCards")
        @Expose
        val strHomeRedCards:String?,
        @SerializedName("strHomeYellowCards")
        @Expose
        val strHomeYellowCards:String?,
        @SerializedName("strHomeLineupGoalkeeper")
        @Expose
        val strHomeLineupGoalkeeper:String?,
        @SerializedName("strHomeLineupDefense")
        @Expose
        val strHomeLineupDefense:String?,
        @SerializedName("strHomeLineupMidfield")
        @Expose
        val strHomeLineupMidfield:String?,
        @SerializedName("strHomeLineupForward")
        @Expose
        val strHomeLineupForward:String?,
        @SerializedName("strHomeLineupSubstitutes")
        @Expose
        val strHomeLineupSubstitutes:String?,
        @SerializedName("strHomeFormation")
        @Expose
        val strHomeFormation:String?,
        @SerializedName("strAwayRedCards")
        @Expose
        val strAwayRedCards:String?,
        @SerializedName("strAwayYellowCards")
        @Expose
        val strAwayYellowCards:String?,
        @SerializedName("strAwayGoalDetails")
        @Expose
        val strAwayGoalDetails:String?,
        @SerializedName("strAwayLineupGoalkeeper")
        @Expose
        val strAwayLineupGoalkeeper:String?,
        @SerializedName("strAwayLineupDefense")
        @Expose
        val strAwayLineupDefense:String?,
        @SerializedName("strAwayLineupMidfield")
        @Expose
        val strAwayLineupMidfield:String?,
        @SerializedName("strAwayLineupForward")
        @Expose
        val strAwayLineupForward:String?,
        @SerializedName("strAwayLineupSubstitutes")
        @Expose
        val strAwayLineupSubstitutes:String?,
        @SerializedName("strAwayFormation")
        @Expose
        val strAwayFormation:String?,
        @SerializedName("intHomeShots")
        @Expose
        val intHomeShots:String?,
        @SerializedName("intAwayShots")
        @Expose
        val intAwayShots:String?,
        @SerializedName("strTimestamp")
        @Expose
        val strTimestamp:String?,
        @SerializedName("dateEvent")
        @Expose
        val dateEvent:String?,
        @SerializedName("dateEventLocal")
        @Expose
        val dateEventLocal:String?,
        @SerializedName("strDate")
        @Expose
        val strDate:String?,
        @SerializedName("strTime")
        @Expose
        val strTime:String?,
        @SerializedName("strTimeLocal")
        @Expose
        val strTimeLocal:String?,
        @SerializedName("strTVStation")
        @Expose
        val strTVStation:String?,
        @SerializedName("idHomeTeam")
        @Expose
        val idHomeTeam:String?,
        @SerializedName("idAwayTeam")
        @Expose
        val idAwayTeam:String?,
        @SerializedName("strResult")
        @Expose
        val strResult:String?,
        @SerializedName("strVenue")
        @Expose
        val strVenue:String?,
        @SerializedName("strCountry")
        @Expose
        val strCountry:String?,
        @SerializedName("strCity")
        @Expose
        val strCity:String?,
        @SerializedName("strPoster")
        @Expose
        val strPoster:String?,
        @SerializedName("strFanart")
        @Expose
        val strFanart:String?,
        @SerializedName("strThumb")
        @Expose
        val strThumb:String?,
        @SerializedName("strBanner")
        @Expose
        val strBanner:String?,
        @SerializedName("strMap")
        @Expose
        val strMap:String?,
        @SerializedName("strTweet1")
        @Expose
        val strTweet1:String?,
        @SerializedName("strTweet2")
        @Expose
        val strTweet2:String?,
        @SerializedName("strTweet3")
        @Expose
        val strTweet3:String?,
        @SerializedName("strVideo")
        @Expose
        val strVideo:String?,
        @SerializedName("strStatus")
        @Expose
        val strStatus:String?,
        @SerializedName("strPostponed")
        @Expose
        val strPostponed:String?,
        @SerializedName("strLocked")
        @Expose
        val strLocked:String

)