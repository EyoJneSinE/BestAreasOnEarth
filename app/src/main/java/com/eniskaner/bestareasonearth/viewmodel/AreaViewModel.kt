package com.eniskaner.bestareasonearth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.bestareasonearth.model.ImageResponse
import com.eniskaner.bestareasonearth.repo.AreaRepositoryInterface
import com.eniskaner.bestareasonearth.roomdb.Area
import com.eniskaner.bestareasonearth.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(
    private val repository: AreaRepositoryInterface
) : ViewModel() {

    //AreaFragment
    val artList = repository.getArea()

    //ImageApiFragment
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    //AreaDetailsFragment
    private var insertAreaMsg = MutableLiveData<Resource<Area>>()
    val insertAreaMessage : LiveData<Resource<Area>>
        get() = insertAreaMsg

    //Solving the Navigation Bug
    fun resetInsertAreaMsg() {
        insertAreaMsg = MutableLiveData<Resource<Area>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArea(area: Area) = viewModelScope.launch {
        repository.deleteArea(area)
    }

    fun insertArea(area: Area) =viewModelScope.launch {
        repository.insertArea(area)
    }
    fun makeArea(areaName: String, areaCityName: String, areaFamousWhy: String, areaSpendAmount: String) {
        if (areaName.isEmpty() || areaCityName.isEmpty() || areaFamousWhy.isEmpty() || areaSpendAmount.isEmpty()){
            insertAreaMsg.postValue(Resource.error("Enter area name, city, famous why, spend amount", null))
        }
        val area = Area(areaName, areaCityName, areaFamousWhy, areaSpendAmount, selectedImage.value ?: "")
        insertArea(area)
        setSelectedImage("")
        insertAreaMsg.postValue(Resource.success(area))

    }

    fun searchForImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }
    }
}