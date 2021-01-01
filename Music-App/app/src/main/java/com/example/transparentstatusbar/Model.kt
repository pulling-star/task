package com.example.transparentstatusbar

class Model(private var image:String, private var text:String){

    fun getImage(): String {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getText(): String{
        return text
    }
}