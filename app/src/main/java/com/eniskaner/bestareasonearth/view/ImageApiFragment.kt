package com.eniskaner.bestareasonearth.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.adapter.ImageRecyclerAdapter
import com.eniskaner.bestareasonearth.databinding.FragmentImageApiBinding
import com.eniskaner.bestareasonearth.util.Status
import com.eniskaner.bestareasonearth.viewmodel.AreaViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageApiFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_image_api) {

    lateinit var viewModel : AreaViewModel

    private var fragmentImageApiBinding : FragmentImageApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AreaViewModel::class.java)

        val binding = FragmentImageApiBinding.bind(view)
        fragmentImageApiBinding = binding

        var job: Job? = null

        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }

        subscribeToObservers()

        binding.imageRecyclerView.apply {
            adapter = imageRecyclerAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        imageRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }
    private fun subscribeToObservers() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult -> imageResult.previewURL }
                    imageRecyclerAdapter.images = urls ?: listOf()
                    fragmentImageApiBinding?.progressBar?.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                    fragmentImageApiBinding?.progressBar?.visibility = View.GONE
                }

                Status.LOADING -> {
                    fragmentImageApiBinding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }
    override fun onDestroyView() {
        fragmentImageApiBinding = null
        super.onDestroyView()
    }
}