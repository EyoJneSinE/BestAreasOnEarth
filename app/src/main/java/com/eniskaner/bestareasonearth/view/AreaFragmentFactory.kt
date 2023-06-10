package com.eniskaner.bestareasonearth.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.eniskaner.bestareasonearth.adapter.AreaRecyclerAdapter
import com.eniskaner.bestareasonearth.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class AreaFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val areaRecyclerAdapter: AreaRecyclerAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            AreaDetailsFragment::class.java.name -> AreaDetailsFragment(glide)
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            AreaFragment::class.java.name -> AreaFragment(areaRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}