package com.mironenko.gifviewer.model

import javax.inject.Inject

class GifListRepository @Inject constructor(private val remoteDataSource: IGifListRemoteDataSource) {

    suspend fun downloadGif(page: Int) = remoteDataSource.downloadGifList(page = page)
}