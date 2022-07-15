package com.mironenko.gifviewer.di

import com.mironenko.gifviewer.screens.giflist.GifListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(gifListFragment: GifListFragment)
}