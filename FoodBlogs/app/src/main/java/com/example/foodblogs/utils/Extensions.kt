package com.example.foodblogs.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object Extensions {

    fun ImageView.setImage(url: String, isCenterCrop: Boolean = false) {
        when {
            isCenterCrop -> Glide.with(this).load(url).apply(RequestOptions().circleCrop()).into(this)
            else -> Glide.with(this).load(url).into(this)
        }
    }
}