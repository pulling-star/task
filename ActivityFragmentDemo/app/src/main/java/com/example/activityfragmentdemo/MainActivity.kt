package com.example.activityfragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.activityfragmentdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"Activity-onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"Activity-onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Activity-onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Activity-onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Activity-onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Activity-onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Activity-onDestroy")
    }
}