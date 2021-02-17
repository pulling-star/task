package com.example.enrollmentapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class MyAdapter(var context: Context,private val fm:FragmentManager,private var totalTabs:Int)
    : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                UsersFragment()
            }
            1 -> {
                EnrollFragment()
            }
            else ->{
                getItem(position)
            }
        }
    }

}