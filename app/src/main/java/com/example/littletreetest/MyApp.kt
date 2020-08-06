package com.example.littletreetest

import android.app.Application
import android.content.Context
import timber.log.Timber

class MyApp : Application(){

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Timber.plant(Timber.DebugTree())
    }
}