package com.mironenko.gifviewer.di

import com.mironenko.gifviewer.GifViewModelFactory
import com.mironenko.gifviewer.screens.giflist.GifGridViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(viewModelFactory: GifViewModelFactory)
    fun inject(gitViewModel: GifGridViewModel)
}