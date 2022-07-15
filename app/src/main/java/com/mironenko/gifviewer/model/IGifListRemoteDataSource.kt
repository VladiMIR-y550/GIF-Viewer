package com.mironenko.gifviewer.model

import retrofit2.Response

interface IGifListRemoteDataSource {
    suspend fun downloadGifList(page: Int): Response<GifEntity>
}