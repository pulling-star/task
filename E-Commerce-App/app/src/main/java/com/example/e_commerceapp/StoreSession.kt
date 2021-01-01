package com.example.e_commerceapp

import android.content.Context
import android.content.SharedPreferences

object StoreSession {
    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context){
        if(sharedPreferences ==null){
            sharedPreferences = context.applicationContext.getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE)
        }
    }

    fun write(key:String, value: Int){
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value.toString())
        editor?.apply()
    }

    fun readString(key: String): String? {
        return sharedPreferences?.getString(key, "")
    }
}