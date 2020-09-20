package com.example.littletreetest


import android.app.Application
import android.content.Context
import com.example.littletreetest.base.SpUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
class MyApp : Application() {

    companion object {
        lateinit var context: Context
    }

    @Inject
    lateinit var spUtil: SpUtil

    override fun onCreate() {
        super.onCreate()
        context = this
        Timber.plant(Timber.DebugTree())

        Timber.d(spUtil.toString())
    }
}