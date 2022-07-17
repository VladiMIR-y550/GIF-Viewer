package com.mironenko.gifviewer.utils

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mironenko.gifviewer.GifViewerApp
import com.mironenko.gifviewer.appComponent
import com.mironenko.gifviewer.model.GifListRepository
import com.mironenko.gifviewer.screens.gifdetails.GifDetailsViewModel
import com.mironenko.gifviewer.screens.giflist.GifGridViewModel
import javax.inject.Inject

class GifViewModelFactory(application: Application) :
    ViewModelProvider.Factory {

    @Inject
    lateinit var repository: GifListRepository

    init {
        application.appComponent.inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            GifGridViewModel::class.java -> {
                Log.d("TAG", "GifViewModelFactory -> $modelClass")
                GifGridViewModel(repository)
            }
            GifDetailsViewModel::class.java -> {
                GifDetailsViewModel(repository)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = GifViewModelFactory(requireContext().applicationContext as GifViewerApp)