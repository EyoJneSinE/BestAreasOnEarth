package com.eniskaner.bestareasonearth.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.adapter.AreaRecyclerAdapter
import com.eniskaner.bestareasonearth.databinding.FragmentAreasBinding
import com.eniskaner.bestareasonearth.viewmodel.AreaViewModel
import javax.inject.Inject

class AreaFragment @Inject constructor(
    val areaRecyclerAdapter: AreaRecyclerAdapter
) : Fragment(R.layout.fragment_areas) {

    lateinit var viewModel : AreaViewModel

    private var fragmentBinding : FragmentAreasBinding? = null
    private val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArea = areaRecyclerAdapter.areas[layoutPosition]
            viewModel.deleteArea(selectedArea)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AreaViewModel::class.java)
        var binding = FragmentAreasBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.recyclerViewAreas.apply {
            adapter = areaRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerViewAreas)

        binding.fabButton.setOnClickListener {
            findNavController().navigate(AreaFragmentDirections.actionAreaFragmentToAreaDetailsFragment())
        }
    }

    private fun subscribeToObservers() {
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            areaRecyclerAdapter.areas = it
        })
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}