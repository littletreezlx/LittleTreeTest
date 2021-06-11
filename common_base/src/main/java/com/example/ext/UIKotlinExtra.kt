package com.example.ext

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.common_base.base.CommonBaseApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import me.jessyan.autosize.utils.AutoSizeUtils
import java.io.File
import kotlin.concurrent.thread



val Float.dp: Int
    get() {
        return AutoSizeUtils.dp2px(CommonBaseApplication.appContext, this)
    }

val Int.dp: Int
    get() {
        return AutoSizeUtils.dp2px(CommonBaseApplication.appContext, this.toFloat())
    }

val Context.layoutInflater: LayoutInflater
    get() {
        return LayoutInflater.from(this)
    }

fun View.expandTouchRange() {
    post {
        val bounds = Rect()
        // 获取View2区域在View1中的相对位置，这里因为View1是View2的直接父View，所以使用getHitRect()
        getHitRect(bounds)
        val expandLength = 30.dp
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

fun ImageView.loadRes(drawableRes: Drawable?) {
    Glide.with(context).load(drawableRes)
        .into(this)
}

fun TextView.setScrollable() {
    movementMethod = ScrollingMovementMethod.getInstance()
}


fun TextView.startAutoScroll(str: SpannableString, space: Long = 50): Thread {
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
            post {
                text = str
            }
        }
    }
//    setOnClickListener {
//        t.interrupt()
//        post {
//           text = str
//        }
//    }
//    (this.parent as View).setOnClickListener {
//        t.interrupt()
//    }
    return t
}


fun TextView.startAutoScroll(
    str: String,
    finishedListener: () -> Unit = {},
    space: Long = 50
): Thread {
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
            finishedListener()
        } catch (e: InterruptedException) {
            e.printStackTrace()
            post {
                text = str
            }
            finishedListener()
        }
    }
    return t
}


fun View.setHeight(height: Int) {
    post {
        layoutParams = layoutParams.apply {
            this.height = height
        }
    }
}


fun View.setWidth(width: Int) {
    post {
        layoutParams = layoutParams.apply {
            this.width = width
        }
    }
}


fun EditText.setOnlyNumber() {
    addTextChangedListener {
        val oldStr = it.toString()
        var newStr = oldStr
        newStr = newStr.replace(" ", "")
        newStr = newStr.replace("-", "")
        newStr = newStr.replace(".", "")
        newStr = newStr.replace(",", "")
        if (newStr != oldStr) {
            setText(newStr)
            setSelection(newStr.length)
//            setSelection(selectionEnd)
        }
    }
}