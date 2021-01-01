package com.example.transparentstatusbar

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2

class OnBoardingActivity : AppCompatActivity(),OnBoardingOneFragment.OnNextCLick,OnBoardingTwoFragment.OnOptionClick {
    companion object {
        private const val FIRST_ITEM = 0
        private const val LAST_ITEM = 1
    }
    lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setUpSharedPreferences()
        checkStatus()
        bindView()
        setTransparentStatusBar()
    }

    private fun checkStatus() {
        val isOnBoarded = StoreSession.read(prefConstant.ON_BOARDED_SUCCESSFULLY)
        if(isOnBoarded!!){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpSharedPreferences() {
        StoreSession.init(this)
    }

    private fun bindView() {
        viewPager=findViewById(R.id.viewPager)
        val pageAdapter = FragmentAdapter(this)
        viewPager.adapter = pageAdapter
    }

    private fun setTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    override fun onClick() {
        viewPager.currentItem = LAST_ITEM
    }

    override fun onOptionBack() {
        viewPager.currentItem = FIRST_ITEM
    }

    override fun onOptionDone() {
        StoreSession.write(prefConstant.ON_BOARDED_SUCCESSFULLY,true)
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}