package com.example.mapdemoapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mapdemoapp.R
import com.example.mapdemoapp.databinding.FragmentMapBinding

class MapFragment : Fragment(R.layout.fragment_map){

    lateinit var binding: FragmentMapBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
    }
}