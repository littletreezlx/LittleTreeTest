package com.example.common_base.utils

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.example.common_base.base.BaseApplication
import kotlin.math.pow
import kotlin.math.sqrt


object SystemUtil {


    fun isPad() = isPadByFlag(BaseApplication.appContext) and isPadByPixel(BaseApplication.appContext)


    private fun isPadByFlag(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }


    //    由于现在市场上，大屏幕手机层出不穷，基本各个都是超过5.5寸屏幕。
    //    所以方法一，调用系统的Configuration.SCREENLAYOUT_SIZE_MASK 基本获取的都是大屏幕尺寸的类型。
    //    因为Android系统代码就只把系统屏幕分成了大、中、小三种。所以使用方法一判断不够严谨，建议使方法二。
    private fun isPadByPixel(context: Context): Boolean {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = wm.defaultDisplay
        // 屏幕宽度
        val screenWidth = display.width
        // 屏幕高度
        val screenHeight = display.height
        val dm = DisplayMetrics()
        display.getMetrics(dm)
        val x = (dm.widthPixels / dm.xdpi).pow(2)
        val y = (dm.heightPixels / dm.ydpi).pow(2)
        // 屏幕尺寸
        val screenInches = sqrt(x + y)
        // 大于6尺寸则为Pad
//        return screenInches >= 6.0

        return screenInches >= 6.5
    }


}