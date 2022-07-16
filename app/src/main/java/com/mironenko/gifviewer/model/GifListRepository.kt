package com.mironenko.gifviewer.model

import android.util.Log
import com.mironenko.gifviewer.toGif
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

typealias GifListener = (gif: List<Gif>) -> Unit

class GifListRepository @Inject constructor(
    private val remoteDataSource: IGifListRemoteDataSource
) {
    var gifBase = mutableListOf<Gif>()
        private set
    private val listeners = mutableListOf<GifListener>()

    suspend fun downloadGif(page: Int) {
        val response = remoteDataSource.downloadGifList(page)
        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                val gifEntity = this@GifListRepository.toGif(it)
                addGif(gifEntity)
                Log.d("TAG", "base size ${gifBase.size}")
            }
            notifyChanges()
        }
    }

    suspend fun listenCurrentGifBase(): Flow<List<Gif>> = callbackFlow {

        val listener: GifListener = {
            trySend(it)
        }
        listeners.add(listener)
        awaitClose {
            listeners.remove(listener)
        }
    }.buffer(Channel.CONFLATED)

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