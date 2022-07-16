package com.mironenko.gifviewer

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.mironenko.gifviewer.di.AppComponent
import com.mironenko.gifviewer.di.DaggerAppComponent
import com.mironenko.gifviewer.di.RepositoryModule

class GifViewerApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule(getGiphyKey()))
            .build()
    }

    private fun getGiphyKey(): String {
        val ai: ApplicationInfo = applicationContext.packageManager.getApplicationInfo(
            applicationContext.packageName,
            PackageManager.GET_META_DATA
        )
        val value = ai.metaData["keyValue"]
        return value.toString()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is GifViewerApp -> appComponent
        else -> this.applicationContext.appComponent
    }