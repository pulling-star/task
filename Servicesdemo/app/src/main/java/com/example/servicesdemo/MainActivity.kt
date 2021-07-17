package com.example.servicesdemo

import android.app.job.JobScheduler
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.servicesdemo.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var serviceIntent : Intent
    lateinit var workManager: WorkManager
    lateinit var workRequest1: OneTimeWorkRequest
    lateinit var workRequest2: OneTimeWorkRequest
    lateinit var workRequest3: OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager = WorkManager.getInstance(applicationContext)
        workRequest1 = OneTimeWorkRequest
            .Builder(RandomNumberGenerator1::class.java).addTag("worker1").build()
        workRequest2 = OneTimeWorkRequest
                .Builder(RandomNumberGenerator2::class.java).addTag("worker2").build()
        workRequest3 = OneTimeWorkRequest
                .Builder(RandomNumberGenerator3::class.java).addTag("worker3").build()
        Log.i(MyService.TAG, "MainActivity thread: " + Thread.currentThread().id)

        serviceIntent = Intent(this, MyService::class.java)

        binding.button.setOnClickListener {
//            workManager.beginWith(workRequest1).then(workRequest2).then(workRequest3).enqueue()
            workManager.beginWith(Arrays.asList(workRequest1,workRequest2)).then(workRequest3).enqueue()
        }

        binding.button2.setOnClickListener {
            workManager.cancelAllWorkByTag("worker2")
        }

    }

}