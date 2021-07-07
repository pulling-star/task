package com.example.musicplayerapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this,R.raw.cheapthrills)

        binding.apply {
            start.setOnClickListener {
                musicPlay()
            }
            pause.setOnClickListener {
                musicPause()
            }
            stop.setOnClickListener {
                musicStop()
            }
        }
    }


    fun musicPlay(){
        mediaPlayer.start()
    }

    fun musicPause(){
        mediaPlayer.pause()
    }

    fun musicStop(){
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(this,R.raw.cheapthrills)
    }
}