package com.eniskaner.bestareasonearth.repo

import androidx.lifecycle.LiveData
import com.eniskaner.bestareasonearth.model.ImageResponse
import com.eniskaner.bestareasonearth.roomdb.Area
import com.eniskaner.bestareasonearth.util.Resource

interface AreaRepositoryInterface {

    suspend fun insertArea(area: Area)

    suspend fun deleteArea(area: Area)

    fun getArea() : LiveData<List<Area>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}