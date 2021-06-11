package com.example.common_base.base

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

abstract class CommonBaseApplication : Application() {


    companion object {
        lateinit var appContext: Context
    }


    init {
        appContext = this
    }



    var currentActivity: WeakReference<CommonBaseActivity>? = null


}