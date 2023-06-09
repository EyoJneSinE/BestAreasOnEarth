package com.eniskaner.bestareasonearth.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.databinding.FragmentAreaDetailsBinding

class AreaDetailsFragment: Fragment(R.layout.fragment_area_details) {

    private var fragmentAreaDetailsBinding : FragmentAreaDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAreaDetailsBinding.bind(view)
        fragmentAreaDetailsBinding = binding


        binding.areaImageView.setOnClickListener {
            findNavController().navigate(AreaDetailsFragmentDirections.actionAreaDetailsFragmentToImageApiFragment())
        }
    }
}