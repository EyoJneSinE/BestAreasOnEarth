package com.eniskaner.bestareasonearth.repo

import androidx.lifecycle.LiveData
import com.eniskaner.bestareasonearth.api.AreaAPI
import com.eniskaner.bestareasonearth.model.ImageResponse
import com.eniskaner.bestareasonearth.roomdb.Area
import com.eniskaner.bestareasonearth.roomdb.AreaDao
import com.eniskaner.bestareasonearth.util.Resource
import java.lang.Exception
import javax.inject.Inject

class AreaRepository @Inject constructor(
    private val areaDao: AreaDao,
    private val areaAPI: AreaAPI
) : AreaRepositoryInterface {
    override suspend fun insertArea(area: Area) {
        areaDao.insertArea(area)
    }

    override suspend fun deleteArea(area: Area) {
        areaDao.deleteArea(area)
    }

    override fun getArea(): LiveData<List<Area>> {
        return areaDao.observeArea()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = areaAPI.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else{
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }
}