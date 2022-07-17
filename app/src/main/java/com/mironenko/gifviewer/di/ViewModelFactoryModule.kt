package com.mironenko.gifviewer.di

import android.content.Context
import com.mironenko.gifviewer.GifViewerApp
import com.mironenko.gifviewer.utils.GifViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideGifViewModelFactory(
        context: Context
    ): GifViewModelFactory = GifViewModelFactory(context.applicationContext as GifViewerApp)
}