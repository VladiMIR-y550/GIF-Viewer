package com.mironenko.gifviewer.model.remote

import com.mironenko.gifviewer.AMOUNT_DOWNLOAD_PAGES
import com.mironenko.gifviewer.model.GifEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("trending")
    suspend fun downloadNews(
        @Query("api_key") API_KEY: String = "",
        @Query("limit") amountPages: Int = AMOUNT_DOWNLOAD_PAGES,
        @Query("offset") page: Int = 0
    ): Response<GifEntity>
}