package com.example.servicesdemo

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

class RandomNumberGenerator3(context: Context, workerParams: WorkerParameters) :
        Worker(context, workerParams) {
    private var randomNumber = 0;
    private var isRandomGeneratorOn = false
    private val MIN = 0
    private val MAX = 100

    init {
        isRandomGeneratorOn = true
    }
    private fun startRandomNumberGenerator() {
        var counter = 0
        while (counter<5) {
            try {
                Thread.sleep(1000)
                if(isRandomGeneratorOn){
                    randomNumber = Random().nextInt(MAX) + MIN
                    Log.i(
                            MyService.TAG, "Worker3 Thread id: " + Thread.currentThread().id + ", Random Number: " + randomNumber)
                    counter++
                }

            } catch (e: InterruptedException) {
                Log.i(MyService.TAG, "Thread Interrupted")
            }

        }

    }

    override fun doWork(): ListenableWorker.Result {
        startRandomNumberGenerator()
        return ListenableWorker.Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        Log.i(MyService.TAG, "Worker has been cancelled")
    }
}