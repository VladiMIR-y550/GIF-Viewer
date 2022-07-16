package com.mironenko.gifviewer.model

import android.util.Log
import com.mironenko.gifviewer.toGif
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias GifListener = (gif: List<Gif>) -> Unit

class GifListRepository @Inject constructor(
    private val remoteDataSource: IGifListRemoteDataSource
) {
    private var gifBase = mutableListOf<Gif>()
    private val listeners = mutableListOf<GifListener>()

    private val _gifFlow = MutableSharedFlow<List<Gif>>()
    val gifFlow: Flow<List<Gif>> = _gifFlow

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

    fun listenCurrentGifBase(): Flow<List<Gif>> = callbackFlow {
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
        }
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(gifBase.toList()) }
    }
}