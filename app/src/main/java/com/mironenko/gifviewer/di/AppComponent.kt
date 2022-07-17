package com.mironenko.gifviewer.di

import com.mironenko.gifviewer.utils.GifViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(viewModelFactory: GifViewModelFactory)
}