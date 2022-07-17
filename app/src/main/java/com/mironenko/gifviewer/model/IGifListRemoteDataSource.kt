package com.mironenko.gifviewer.model

import com.mironenko.gifviewer.model.remote.GifListEntity
import retrofit2.Response

interface IGifListRemoteDataSource {
    suspend fun downloadGifList(page: Int): Response<GifListEntity>
    suspend fun searchGifList(page: Int, searchQuery: String): Response<GifListEntity>
}