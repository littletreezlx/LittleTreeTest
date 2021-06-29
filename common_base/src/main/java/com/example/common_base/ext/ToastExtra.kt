package com.example.common_base.ext

import android.content.Context
import android.widget.Toast
import com.example.common_base.base.BaseApplication
import timber.log.Timber


var lastToast: Toast = Toast.makeText(BaseApplication.appContext, "", Toast.LENGTH_SHORT)


fun showToast(message: String) {
    //主线程
    if ("main" == Thread.currentThread().name) {
        Timber.d("ShowToast: ${Thread.currentThread().name}")
        showToastReally(message)
    } else {
        //异步
        BaseApplication.getInstance().currentActivity?.get()?.let {
            it.runOnUiThread {
                showToastReally(message, it)
            }
        }
    }
}


//兼容子线程显示toast，加上looper
//fun showToast(message: String) {
//    //主线程
//    if ("main" == Thread.currentThread().name) {
//        Timber.d("ShowToast: ${Thread.currentThread().name}")
//        showToastReally(message)
//    } else {
//        //异步
//        var myLooper = Looper.myLooper()
//        if (myLooper == null) {
//            Looper.prepare()
//            myLooper = Looper.myLooper()
//            Timber.d("ShowToast: ${myLooper?.thread?.name}")
//        }
//        showToastReally(message)
//        myLooper?.let {
//            Looper.loop()
//            myLooper.quit()
//        }
//    }
//}


private fun showToastReally(message: String, context: Context = BaseApplication.appContext) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    if (lastToast.view?.isShown == true) {
        lastToast.cancel()
    }
    toast.show()
    lastToast = toast
}
