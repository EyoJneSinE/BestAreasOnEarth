package com.eniskaner.bestareasonearth.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AreaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArea(area: Area)

    @Delete
    suspend fun deleteArea(area: Area)

    @Query("SELECT * FROM area")
    fun observeArea(): LiveData<List<Area>>

}