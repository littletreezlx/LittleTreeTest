package com.example.common_base.base

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

abstract class BaseApplication : Application() {


    companion object {
        lateinit var appContext: Context

        fun getInstance(): BaseApplication = (appContext) as BaseApplication

    }


    init {
        appContext = this
    }



    var currentActivity: WeakReference<BaseActivity>? = null


}