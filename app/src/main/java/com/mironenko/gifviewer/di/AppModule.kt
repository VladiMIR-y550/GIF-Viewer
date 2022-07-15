package com.mironenko.gifviewer.di

import dagger.Module

@Module(includes = [RetrofitModule::class, RepositoryModule::class])
class AppModule