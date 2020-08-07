package com.mixu.jingtu.common.ext

import android.graphics.Rect
import android.text.method.ScrollingMovementMethod
import android.view.TouchDelegate
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.littletreetest.MyApp

import me.jessyan.autosize.utils.AutoSizeUtils
import java.io.File
import kotlin.concurrent.thread


fun dp2px(value: Float): Int{
    return AutoSizeUtils.dp2px(MyApp.context, value)
}


fun View.expandTouchRange() {
    post {
        val bounds = Rect()
        // 获取View2区域在View1中的相对位置，这里因为View1是View2的直接父View，所以使用getHitRect()
        getHitRect(bounds)
        val expandLength = dp2px(30f)
        bounds.left -= expandLength
        bounds.top -= expandLength
        bounds.right += expandLength
        bounds.bottom += expandLength
        val touchDelegate = TouchDelegate(bounds, this)
        ((parent) as View).touchDelegate = touchDelegate
    }
}



fun ImageView.loadCircleRes(file: File) {
    Glide.with(context).load(file)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}

fun ImageView.loadCircleRes(file: String) {
    Glide.with(context).load(file)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}


fun ImageView.loadRes(drawableRes: Int) {
    Glide.with(context).load(drawableRes)
        .into(this)
}


fun TextView.setScrollable(){
    movementMethod = ScrollingMovementMethod.getInstance()
}


fun TextView.startAutoScroll(str: String, space: Long = 100) {
    val t = thread {
        var l = 1
        try {
            val max = str.length
            while (l <= max) {
                val stv = str.substring(0, l)
                post {
                    text = stv
                }
                Thread.sleep(space)
                l++
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
    setOnClickListener {
        t.interrupt()
        post {
           text = str
        }
    }
}
