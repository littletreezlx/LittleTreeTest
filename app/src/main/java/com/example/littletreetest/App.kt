package com.example.littletreetest


import android.app.Application
import android.content.Context
import com.example.common_base.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class App : BaseApplication() {

    companion object {
//        fun getInstance(): App = (appContext) as App
    }


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}