package com.eniskaner.bestareasonearth.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.databinding.FragmentAreaDetailsBinding
import com.eniskaner.bestareasonearth.util.Status
import com.eniskaner.bestareasonearth.viewmodel.AreaViewModel
import javax.inject.Inject

class AreaDetailsFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_area_details) {

    lateinit var viewModel : AreaViewModel

    private var fragmentAreaDetailsBinding : FragmentAreaDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AreaViewModel::class.java)

        val binding = FragmentAreaDetailsBinding.bind(view)
        fragmentAreaDetailsBinding = binding

        subscribeToObservers()

        binding.areaImageView.setOnClickListener {
            findNavController().navigate(AreaDetailsFragmentDirections.actionAreaDetailsFragmentToImageApiFragment())
        }

        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        binding.saveButton.setOnClickListener {
            viewModel.makeArea(binding.areaNameText.text.toString(),
            binding.areaCityNameText.text.toString(),
            binding.areaFamousWhyText.text.toString(),
            binding.areaSpendMoneyText.text.toString())
        }
    }
    private fun subscribeToObservers() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            fragmentAreaDetailsBinding?.let {
                glide.load(url).into(it.areaImageView)
            }
        })

        viewModel.insertAreaMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(),"Success",Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                    viewModel.resetInsertAreaMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAreaDetailsBinding = null

    }
}