package com.mironenko.gifviewer.model.remote

import com.mironenko.gifviewer.utils.AMOUNT_DOWNLOAD_PAGES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("trending")
    suspend fun downloadGif(
        @Query("api_key") API_KEY: String,
        @Query("limit") amountPages: Int = AMOUNT_DOWNLOAD_PAGES,
        @Query("offset") page: Int = 0
    ): Response<GifListEntity>


    @GET("search")
    suspend fun searchGif(
        @Query("api_key") API_KEY: String,
        @Query("q") search: String = "",
        @Query("limit") amountPages: Int = AMOUNT_DOWNLOAD_PAGES,
        @Query("offset") page: Int = 0,
        @Query("rating") rating: String = "q",
        @Query("lang") lang: String = "en"
    ): Response<GifListEntity>
}