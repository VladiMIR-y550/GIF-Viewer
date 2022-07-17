package com.mironenko.gifviewer.screens.gifdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.model.GifListRepository

class GifDetailsViewModel(
    private val repository: GifListRepository
) : ViewModel() {
    private var gifId: String = ""
    private var _gifDetails = MutableLiveData<Gif>()
    val gifDetails: LiveData<Gif> = _gifDetails

    fun saveCurrentGifId(currentGifId: String) {
        gifId = currentGifId
        _gifDetails.postValue(getGifById(currentGifId))
    }

    private fun getGifById(gifId: String): Gif? {
        val indexToGet = repository.gifBase.indexOfFirst { it.id == gifId }
        return if (indexToGet != -1) repository.gifBase[indexToGet] else null
    }
}

