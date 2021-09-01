package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding
    lateinit var chatTab :TabLayout.Tab

    val list = mapOf( 0 to "chats", 1 to "status", 2 to "calls")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        chatTab = binding.tabs.getTabAt(2)!!
        setUpViewPager()

        val colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColorStateList(R.color.tab_icon, activity?.theme)
        } else {
            TODO("VERSION.SDK_INT < M")
        }

        for (i in 0 until binding.tabs.tabCount) {
            val tab: TabLayout.Tab = binding.tabs.getTabAt(i)!!
            var icon = tab.icon
            if (icon != null) {
                icon = DrawableCompat.wrap(icon)
                DrawableCompat.setTintList(icon, colors)
            }
        }
    }

    private fun setUpViewPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabs

        val adapter = TabsAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = list[position]
            viewPager.setCurrentItem(tab.position,true)
        }.attach()
        tabLayout.selectTab(chatTab)

    }

}