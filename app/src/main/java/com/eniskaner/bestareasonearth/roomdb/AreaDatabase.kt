package com.eniskaner.bestareasonearth.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Area::class], version = 1)
abstract class AreaDatabase: RoomDatabase() {
    abstract fun areaDao(): AreaDao
}