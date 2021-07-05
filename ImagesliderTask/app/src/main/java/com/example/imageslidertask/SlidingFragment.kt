package com.example.imageslidertask

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.imageslidertask.databinding.ImageLayoutBinding

class SlidingFragment:Fragment(R.layout.image_layout) {
    lateinit var binding: ImageLayoutBinding

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val fragment = SlidingFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ImageLayoutBinding.bind(view)
        val position = requireArguments().getInt(ARG_POSITION)
        val landingImagesArray = requireContext().resources.getStringArray(R.array.image_urls_array)

        Glide.with(this)
            .load(landingImagesArray[position])
            .into(binding.slidingImage)

    }
}