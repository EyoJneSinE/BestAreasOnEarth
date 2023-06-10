package com.eniskaner.bestareasonearth.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class AreaFragmentFactory @Inject constructor(
    private val glide: RequestManager
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            AreaDetailsFragment::class.java.name -> AreaDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}