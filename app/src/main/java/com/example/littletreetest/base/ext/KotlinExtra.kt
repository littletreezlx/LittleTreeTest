package com.mixu.jingtu.common.ext

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.os.Looper
import android.view.TouchDelegate
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.littletreetest.MyApp
import timber.log.Timber
import java.io.Serializable
import java.util.concurrent.Executors


private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
//private val IO_EXECUTOR = Executors.


//fun runOnUIThread(f: () -> Unit) {
//    IO_EXECUTOR.execute(f)
//}


fun runOnIOThread(f: () -> Unit) {

    IO_EXECUTOR.execute(f)
}

var lastToast: Toast = Toast.makeText(MyApp.context, "", Toast.LENGTH_SHORT)


//fun showToast(message: String) {
//    val toast = Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT)
//    if (lastToast.view.isShown) {
//        lastToast.cancel()
//    }
//    toast.show()
//    lastToast = toast
//}


//兼容子线程显示toast，加上looper
fun showToast(message: String) {
    //主线程
    if ("main" == Thread.currentThread().name) {
        Timber.d("ShowToast: ${Thread.currentThread().name}")
        showToastReally(message)
    } else {
        //异步
        var myLooper = Looper.myLooper()
        if (myLooper == null) {
            Looper.prepare()
            myLooper = Looper.myLooper()
            Timber.d("ShowToast: ${myLooper?.thread?.name}")
        }
        showToastReally(message)
        myLooper?.let {
            Looper.loop()
            myLooper.quit()
        }
    }
}


private fun showToastReally(message: String) {
    val toast = Toast.makeText(MyApp.context, message, Toast.LENGTH_SHORT)
    if (lastToast.view.isShown) {
        lastToast.cancel()
    }
    toast.show()
    lastToast = toast
}




/**
 * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
 *
 * @param context
 * @return 平板返回 True，手机返回 False
 */
fun isPad(): Boolean {
    return ((MyApp.context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}

//fun EditText.getEditStr(): String{
//    return text.toString()
//}

fun EditText.isAvailable(): Boolean {
    return !text.isEmpty() && !text.trim().isEmpty()
}


fun EditText.isEmpty(): Boolean {
    return text.isEmpty() || text.trim().isEmpty()
}


fun TextView.isEmpty(): Boolean {
    return text.isEmpty() || text.trim().isEmpty()
}


fun EditText.getString(): String {
    if (text.isEmpty()) {
        return ""
    } else {
        return text.trim().toString()
    }
}

fun TextView.getString(): String {
    if (text.isEmpty()) {
        return ""
    } else {
        return text.trim().toString()
    }
}


fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}




fun Fragment.setArguments(vararg arguments: Pair<String, Any>) {
    val bundle = Bundle()
    for (argument in arguments) {
        val key = argument.first
        when (val value = argument.second) {
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)
            is Short -> bundle.putShort(key, value)
            is Float -> bundle.putFloat(key, value)
            is Double -> bundle.putDouble(key, value)
            is Boolean -> bundle.putBoolean(key, value)
            is String -> bundle.putString(key, value)
            is Serializable -> bundle.putSerializable(key, value)
        }
    }
    this.arguments = bundle
}


//fun DialogFragment.show(fragmentManager: FragmentManager) {
//    this.show(fragmentManager, this.javaClass.simpleName)
//}




