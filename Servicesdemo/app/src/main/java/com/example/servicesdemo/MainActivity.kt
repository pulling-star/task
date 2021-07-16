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
import com.example.servicesdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var serviceIntent : Intent
    lateinit var jobScheduler: JobScheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        Log.i(MyService.TAG, "MainActivity thread: " + Thread.currentThread().id)

        serviceIntent = Intent(this, MyService::class.java)

        binding.button.setOnClickListener {
            startJob()
        }

        binding.button2.setOnClickListener {
            stopJob()
        }

    }

    private fun startJob() {
        val componentName = ComponentName(this,MyService::class.java)
        val jobInfo = JobInfo.Builder(101,componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_CELLULAR)
            .setPeriodic(15*60*1000)
            .setRequiresCharging(false)
            .setPersisted(true)
            .build()

        if(jobScheduler.schedule(jobInfo) == JobScheduler.RESULT_SUCCESS){
            Log.i(MyService.TAG, "MainActivity thread: " + Thread.currentThread().id + ", Job successfully scheduled")
        }else{
            Log.i(MyService.TAG, "MainActivity thread: " + Thread.currentThread().id + ", Job could not be scheduled")
        }
    }

    private fun stopJob() {
        jobScheduler.cancel(101)
    }

}