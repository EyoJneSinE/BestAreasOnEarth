package com.eniskaner.bestareasonearth.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "area")
data class Area(
    var areaName: String,
    var areaCityName: String,
    var areaWhyFamous: String,
    var areaSpendAmount: String,
    var imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)