package com.example.contentlistapp.utils

import com.example.contentlistapp.R

object Utility {

    //Mapping response images with drawables
    fun getMappedImage(image:String):Int{
        return when(image){
            "poster1.jpg" -> {
                R.drawable.poster1
            }
            "poster2.jpg" -> {
                R.drawable.poster2
            }
            "poster3.jpg" -> {
                R.drawable.poster3
            }
            "poster4.jpg" -> {
                R.drawable.poster4
            }
            "poster5.jpg" -> {
                R.drawable.poster5
            }
            "poster6.jpg" -> {
                R.drawable.poster6
            }
            "poster7.jpg" -> {
                R.drawable.poster7
            }
            "poster8.jpg" -> {
                R.drawable.poster8
            }
            "poster9.jpg" -> {
                R.drawable.poster9
            }
            else -> {
                R.drawable.placeholder_for_missing_posters
            }
        }
    }

    fun getPageData(pageNumber:Int): String{
        return when(pageNumber){
            1 -> "CONTENTLISTINGPAGE-PAGE1.json"
            2 -> "CONTENTLISTINGPAGE-PAGE2.json"
            else -> "CONTENTLISTINGPAGE-PAGE3.json"
        }
    }
}