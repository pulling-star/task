package com.example.servicesdemo

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.servicesdemo.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var serviceIntent : Intent
    lateinit var jobScheduler: JobScheduler
    lateinit var workManager: WorkManager
    lateinit var workRequest: WorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager = WorkManager.getInstance(applicationContext)
        workRequest = PeriodicWorkRequest
            .Builder(RandomNumberGenerator::class.java,15,TimeUnit.MINUTES).build()

        Log.i(MyService.TAG, "MainActivity thread: " + Thread.currentThread().id)

        serviceIntent = Intent(this, MyService::class.java)

        binding.button.setOnClickListener {
            workManager.enqueue(workRequest)
        }

        binding.button2.setOnClickListener {
            workManager.cancelWorkById(workRequest.id)
        }

    }

}