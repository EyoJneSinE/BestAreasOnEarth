package com.eniskaner.bestareasonearth.api

import com.eniskaner.bestareasonearth.model.ImageResponse
import com.eniskaner.bestareasonearth.util.APIKey.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AreaAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuerry : String,
        @Query("key") apiKey : String = API_KEY
    ) : Response<ImageResponse>
}