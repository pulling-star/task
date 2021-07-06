package com.example.imageslidertask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.imageslidertask.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    var imagesArray = ArrayList<String>()
    private var currentPage = 0
    private lateinit var slidingImageDots: Array<ImageView?>
    private var slidingDotsCount = 0
    var imagesUrlArray  = ArrayList<String>()
    lateinit var binding: ActivityMainBinding

    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            for (i in 0 until slidingDotsCount) {
                slidingImageDots[i]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.non_active_dot
                    )
                )
            }

            slidingImageDots[position]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.active_dot
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imagesUrlArray.add("https://cdn.pixabay.com/photo/2020/02/13/10/29/bees-4845211__340.jpg")
        imagesUrlArray.add("https://cdn.pixabay.com/photo/2020/04/24/08/57/street-5085971__340.jpg")
        imagesUrlArray.add("https://cdn.pixabay.com/photo/2020/03/11/01/53/landscape-4920705__340.jpg")
        imagesUrlArray.add("https://cdn.pixabay.com/photo/2020/02/11/12/07/portofino-4839356__340.jpg")
        setUpSlidingViewPager()
    }

    private fun setUpSlidingViewPager() {
        imagesArray = imagesUrlArray

        val landingImagesAdapter = ViewPagerAdapter(this, imagesArray.size)
        binding.slidingViewPager.apply {
            adapter = landingImagesAdapter
            registerOnPageChangeCallback(slidingCallback)
        }

        slidingDotsCount = imagesArray.size

        slidingImageDots = arrayOfNulls(slidingDotsCount)

        for (i in 0 until slidingDotsCount) {
            slidingImageDots[i] = ImageView(this)
            slidingImageDots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.non_active_dot
                )
            )
            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            params.setMargins(8, 0, 8, 0)
            binding.sliderDots.addView(slidingImageDots[i], params)
        }

        slidingImageDots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.active_dot
            )
        )

        val handler = Handler()
        val update = Runnable {
            if (currentPage == imagesArray.size) {
                currentPage = 0
            }

            //The second parameter ensures smooth scrolling
            binding.slidingViewPager.setCurrentItem(currentPage++, true)
        }

        Timer().schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.slidingViewPager.unregisterOnPageChangeCallback(slidingCallback)
    }
}