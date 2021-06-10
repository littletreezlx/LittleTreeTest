package com.example.littletreetest


import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var appContext: Context


        fun getInstance(): App = (appContext) as App
    }


    override fun onCreate() {
        super.onCreate()
        appContext = this
        Timber.plant(Timber.DebugTree())

    }
}