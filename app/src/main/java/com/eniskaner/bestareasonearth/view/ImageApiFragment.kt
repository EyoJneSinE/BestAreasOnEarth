package com.eniskaner.bestareasonearth.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ImageApiFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_image_api) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}