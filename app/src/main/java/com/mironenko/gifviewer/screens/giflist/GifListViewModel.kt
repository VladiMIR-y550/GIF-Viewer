package com.mironenko.gifviewer.screens.giflist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mironenko.gifviewer.model.GifEntity
import com.mironenko.gifviewer.model.GifListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifListViewModel(
    private val gifListRepository: GifListRepository,
    application: Application
) : AndroidViewModel(application) {

    init {
        subscribeGifList()
    }

    var getGif: GifEntity? = null

    private fun subscribeGifList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = gifListRepository.downloadGif(0)
            if (response.isSuccessful) {
                getGif = response.body()
            }
        }
    }
}