package com.example.e_commerceapp.ProductActivity

import android.graphics.drawable.Drawable
import android.widget.ImageView

class Model(private var image:Int, private var text:String){

    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int) {
        this.image = image
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getText(): String{
        return text
    }
}