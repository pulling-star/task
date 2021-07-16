package com.example.servicesdemo

import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import java.util.*


class MyService: JobIntentService() {

    companion object{
        const val TAG = "MyService"

        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, MyService::class.java, 101, intent)
        }
    }

    private var randomNumber = 0;
    private var isRandomGeneratorOn = false
    private val MIN = 0
    private val MAX = 100

//    inner class MyServiceBinder : Binder() {
//        val service: MyService
//            get() = this@MyService
//    }
//
//    private val mBinder:IBinder = MyServiceBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand Called")

        Log.i(TAG, "My Service thread: " + Thread.currentThread().id)
//        isRandomGeneratorOn = true
//        Thread(object : Runnable {
//            override fun run() {
//                startRandomNumberGenerator()
//            }
//
//        }).start()
        return START_STICKY
    }

    override fun onHandleWork(intent: Intent) {
        Log.i(TAG, "OnHandlework thread,thread id: " + Thread.currentThread().id)
        isRandomGeneratorOn = true
        startRandomNumberGenerator(intent.getStringExtra("starter"))
    }


    private fun startRandomNumberGenerator(stringExtra: String?) {
        for (i in 0..5) {
            try {
                if(isStopped()){
                    Log.i(TAG, "Sorry, JobScheduler is force stopping the thread: "+ isStopped());
                    return;
                }
                Thread.sleep(2000)
                randomNumber = Random().nextInt(MAX) + MIN
                Log.i(TAG, "Thread id: " + Thread.currentThread().id + ", Random Number: " + randomNumber +" ," + stringExtra)

            } catch (e: InterruptedException) {
                Log.i(TAG, "Thread Interrupted")
            }
        }
        Log.i(TAG,"Service stopped");
        stopSelf();
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

//    override fun onBind(intent: Intent): IBinder? {
//        Log.i(TAG, "In onBind")
//        return super.onBind(intent)
//    }
//
//    override fun onUnbind(intent: Intent?): Boolean {
//        Log.i(TAG, "In onUnbind")
//        return super.onUnbind(intent)
//
//    }


    override fun onStopCurrentWork(): Boolean {
        Log.i(TAG, "OnStopCurrentWork thread,thread id: " + Thread.currentThread().id)
        return super.onStopCurrentWork()

    }
}