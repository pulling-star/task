package com.example.servicesdemo

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import java.util.*


class MyService: JobService() {

    companion object{
        const val TAG = "MyService"
    }

    private var randomNumber = 0;
    private var isRandomGeneratorOn = false
    private val MIN = 0
    private val MAX = 100

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.i(TAG, "OnSTartJob")
        doBackgroundWork()
        return true
    }

    private fun doBackgroundWork() {
        Thread(object : Runnable{
            override fun run() {
                isRandomGeneratorOn = true
                startRandomNumberGenerator()
            }
        }).start()
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i(TAG, "OnStopJob")
        return true
    }


    private fun startRandomNumberGenerator() {
        while (isRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if(isRandomGeneratorOn){
                    randomNumber = Random().nextInt(MAX) + MIN
                    Log.i(TAG, "Thread id: " + Thread.currentThread().id + ", Random Number: " + randomNumber)
                }

            } catch (e: InterruptedException) {
                Log.i(TAG, "Thread Interrupted")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        isRandomGeneratorOn = false
        Log.d(TAG, "onDestroy Called")

    }


}