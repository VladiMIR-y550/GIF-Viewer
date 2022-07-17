package com.mironenko.gifviewer.di

import dagger.Module

@Module(includes = [ViewModelFactoryModule::class, RetrofitModule::class, RepositoryModule::class])
class AppModule