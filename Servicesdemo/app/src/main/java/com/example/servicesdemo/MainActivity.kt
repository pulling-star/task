package com.example.servicesdemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.servicesdemo.MyService.MyServiceBinder
import com.example.servicesdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    private var myService: MyService? = null
    private var isServiceBound = false
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(MyService.TAG, "MainActivity thread: " + Thread.currentThread().id)

        binding.button.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        binding.button2.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

        binding.buttonBindService.setOnClickListener {
            bindService()
        }

        binding.buttonUnBindService.setOnClickListener {
            unBindService()
        }
        
        binding.buttonGetRandomNumber.setOnClickListener {
            setRandomNumber()
        }
    }

    private fun unBindService() {
        if(isServiceBound){
            serviceConnection?.let { unbindService(it) }
            isServiceBound=false;
        }
    }

    private fun bindService() {
        if (serviceConnection == null) {
            serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                    val myServiceBinder = iBinder as MyServiceBinder
                    myService = myServiceBinder.service
                    isServiceBound = true
                }

                override fun onServiceDisconnected(componentName: ComponentName) {
                    isServiceBound = false
                }
            }
        }
        bindService(
            Intent(this, MyService::class.java),
            serviceConnection!!,
            Context.BIND_AUTO_CREATE
        )
    }

    private fun setRandomNumber() {
        if (isServiceBound) {
            binding.textViewthreadCount.setText("Random number: " + myService?.getRandomNumber())
        } else {
            binding.textViewthreadCount.setText("Service not bound")
        }
    }
}