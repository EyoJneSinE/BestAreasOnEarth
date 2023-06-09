package com.eniskaner.bestareasonearth.di

import android.content.Context
import androidx.room.Room
import com.eniskaner.bestareasonearth.api.AreaAPI
import com.eniskaner.bestareasonearth.roomdb.AreaDatabase
import com.eniskaner.bestareasonearth.util.APIKey.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AreaDatabase::class.java, "AreaDB").build()

    @Singleton
    @Provides
    fun injectDao(
        database: AreaDatabase
    ) = database.areaDao()

    @Singleton
    @Provides
    fun injectAreaAPI(): AreaAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(AreaAPI::class.java)
    }
}