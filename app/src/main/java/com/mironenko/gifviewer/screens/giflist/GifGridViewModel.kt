package com.mironenko.gifviewer.screens.giflist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.model.GifListRepository
import com.mironenko.gifviewer.utils.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifGridViewModel(
    private val repository: GifListRepository
) : ViewModel(), AdapterActionListener {

    private var gifBaseSize: Int = 0
    var searchQuery: String = ""
        private set

    private val _gifList = MutableLiveData<Result<List<Gif>>>()
    val gifList: LiveData<Result<List<Gif>>> = _gifList

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

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

    fun downloadGif(page: Int, searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.downloadGif(page, searchQuery)
        }
    }

    fun downloadBySearchQueryGif(page: Int = START_PAGE, receivedSearchQuery: String) {
        searchQuery = receivedSearchQuery
        repository.clearBase()
        downloadGif(page, receivedSearchQuery)
    }

    fun updateGifBase() {
        downloadGif(repository.gifBase.size - AMOUNT_DOWNLOAD_PAGES, searchQuery)
    }

    override fun loadMoreGif(page: Int) {
        gifResult = PendingResult()
        downloadGif(page, searchQuery)
    }

    private fun onError(errorBody: Throwable) {
        gifResult = ErrorResult(errorBody)
    }

    private fun notifyUpdates() {
        _gifList.postValue(gifResult)
    }
}