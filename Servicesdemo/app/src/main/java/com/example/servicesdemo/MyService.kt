package com.example.servicesdemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*


class MyService:Service() {

    companion object{
        const val TAG = "MyService"
    }

    private var randomNumber = 0;
    private var isRandomGeneratorOn = false
    private val MIN = 0
    private val MAX = 100

    inner class MyServiceBinder : Binder() {
        val service: MyService
            get() = this@MyService
    }

    private val mBinder:IBinder = MyServiceBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand Called")

        Log.i(TAG, "My Service thread: " + Thread.currentThread().id)
        isRandomGeneratorOn = true
        Thread(object : Runnable {
            override fun run() {
                startRandomNumberGenerator()
            }

        }).start()
        return START_STICKY
    }

    private fun startRandomNumberGenerator() {
        while (isRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if (isRandomGeneratorOn) {
                    randomNumber = Random().nextInt(MAX) + MIN
                    Log.i(TAG, "Thread id: " + Thread.currentThread().id + ", Random Number: " + randomNumber)
                }
            } catch (e: InterruptedException) {
                Log.i(TAG, "Thread Interrupted")
            }
        }
    }

    private fun stopRandomNumberGenerator() {
        isRandomGeneratorOn = false
    }

    fun getRandomNumber(): Int {
        return randomNumber
    }


    override fun onDestroy() {
        super.onDestroy()
        stopRandomNumberGenerator()
        Log.d(TAG, "onDestroy Called")

    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "onBind Called")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "In onUnbind")
        return super.onUnbind(intent)

    }
}