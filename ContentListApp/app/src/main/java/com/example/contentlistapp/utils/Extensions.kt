package com.example.contentlistapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.contentlistapp.R

object Extensions {

    fun ImageView.loadImageViewWithDrawable(drawable:Int){
        Glide.with(this).load(drawable).placeholder(R.drawable.placeholder_for_missing_posters).into(this)
    }

}