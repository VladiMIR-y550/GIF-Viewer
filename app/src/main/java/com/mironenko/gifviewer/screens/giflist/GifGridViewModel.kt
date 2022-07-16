package com.mironenko.gifviewer.screens.giflist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironenko.gifviewer.*
import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.model.GifListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifGridViewModel(
    private val repository: GifListRepository
) : ViewModel(), AdapterActionListener {

    private var gifBaseSize: Int = 0

    private val _gifList = MutableLiveData<Result<List<Gif>>>()
    val gifList: LiveData<Result<List<Gif>>> = _gifList

    private var gifResult: Result<List<Gif>> = EmptyResult()
        set(value) {
            field = value
            notifyUpdates()
        }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            repository.listenCurrentGifBase()
                .collect {
                    gifResult = if (it.isEmpty()) {
                        EmptyResult()
                    } else {
                        gifBaseSize = it.size
                        SuccessResult(it)
                    }
                }
        }
    }

    fun downloadGif(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.downloadGif(page)
        }
    }

    fun updateGifBase() {
        downloadGif(repository.gifBase.size - AMOUNT_DOWNLOAD_PAGES)
    }

    override fun loadMoreGif(page: Int) {
        gifResult = PendingResult()
        downloadGif(page)
    }

    private fun notifyUpdates() {
        _gifList.postValue(gifResult)
    }
}