package com.mironenko.gifviewer.model

import android.util.Log
import com.mironenko.gifviewer.model.remote.GifListEntity
import com.mironenko.gifviewer.utils.toGif
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

typealias GifListener = (gif: List<Gif>) -> Unit

class GifListRepository @Inject constructor(
    private val remoteDataSource: IGifListRemoteDataSource
) {
    var gifBase = mutableListOf<Gif>()
        private set
    private val listeners = mutableListOf<GifListener>()

    suspend fun listenCurrentGifBase(): Flow<List<Gif>> = callbackFlow {
        val listener: GifListener = {
            trySend(it)
        }
        listeners.add(listener)
        awaitClose {
            listeners.remove(listener)
        }
    }.buffer(Channel.CONFLATED)

    fun clearBase() {
        gifBase.clear()
        Log.d("TAG", "${gifBase.size}")
    }

    suspend fun downloadGif(page: Int, searchQuery: String) {
        val response: Response<GifListEntity> = getResponse(page, searchQuery)
        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                val gifEntity = this@GifListRepository.toGif(it)
                addGif(gifEntity)
            }
            notifyChanges()
        }
    }

    private suspend fun getResponse(page: Int, searchQuery: String): Response<GifListEntity> =
        if (searchQuery.isNotBlank()) remoteDataSource.searchGifList(
            page,
            searchQuery
        ) else remoteDataSource.downloadGifList(page)

    private fun addGif(gif: Gif) {
        val indexToAdd = gifBase.indexOfFirst { it.id == gif.id }
        if (indexToAdd == -1) {
            gifBase.add(gif)
            notifyChanges()
        } else {
            gifBase[indexToAdd] = gif
        }
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(gifBase.toList()) }
    }
}