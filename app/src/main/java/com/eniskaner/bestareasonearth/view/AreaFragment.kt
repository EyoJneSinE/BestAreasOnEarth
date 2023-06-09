package com.eniskaner.bestareasonearth.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.databinding.FragmentAreasBinding

class AreaFragment: Fragment(R.layout.fragment_areas) {

    private var fragmentBinding : FragmentAreasBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = FragmentAreasBinding.bind(view)
        fragmentBinding = binding

        binding.fabButton.setOnClickListener {
            findNavController().navigate(AreaFragmentDirections.actionAreaFragmentToAreaDetailsFragment())
        }
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}