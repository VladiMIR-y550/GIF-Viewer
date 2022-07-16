package com.mironenko.gifviewer.screens.giflist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironenko.gifviewer.EmptyResult
import com.mironenko.gifviewer.PendingResult
import com.mironenko.gifviewer.Result
import com.mironenko.gifviewer.SuccessResult
import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.model.GifListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class GifGridViewModel(
    private val repository: GifListRepository
) : ViewModel() {

    private val _gifList = MutableLiveData<Result<List<Gif>>>()
    val gifList: LiveData<Result<List<Gif>>> = _gifList

    private var gifResult: Result<List<Gif>> = EmptyResult()
        set(value) {
            field = value
            notifyUpdates()
        }

    init {
        viewModelScope.launch {
            repository.listenCurrentGifBase()
                .collect {
                    _gifList.postValue(SuccessResult(it))
                }
        }
        subscribeGif()
    }

    fun subscribeGif() {
        gifResult = PendingResult()
        repository.gifFlow.shareIn(viewModelScope, SharingStarted.Eagerly, 1)
            .onEach { gifList ->
                gifResult = if (gifList.isEmpty()) {
                    EmptyResult()
                } else {
                    SuccessResult(gifList)
                }
                notifyUpdates()
            }.launchIn(viewModelScope)
    }

    fun downloadGif() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.downloadGif(0)
        }
    }

    private fun notifyUpdates() {
        _gifList.postValue(gifResult)
    }
}