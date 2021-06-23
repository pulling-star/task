package com.example.mapdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mapdemoapp.adapter.ViewPagerAdapter
import com.example.mapdemoapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    lateinit var binding:ActivityMainBinding

    val list = arrayOf("Marker List","Map")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpViewPager()

    }

    private fun setUpViewPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.profileTab

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = list[position]
        }.attach()
    }
}