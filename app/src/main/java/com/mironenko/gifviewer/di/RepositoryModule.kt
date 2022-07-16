package com.mironenko.gifviewer.di

import com.mironenko.gifviewer.model.GifListRepository
import com.mironenko.gifviewer.model.IGifListRemoteDataSource
import com.mironenko.gifviewer.model.remote.GifListRemoteDataSource
import com.mironenko.gifviewer.model.remote.GiphyApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val API_KEY: String) {

    @Provides
    @Singleton
    fun provideGifListDataSource(giphyApi: GiphyApi): IGifListRemoteDataSource =
        GifListRemoteDataSource(giphyApi, API_KEY)

    @Provides
    @Singleton
    fun provideGifListRepository(remote: IGifListRemoteDataSource): GifListRepository =
        GifListRepository(remote)
}