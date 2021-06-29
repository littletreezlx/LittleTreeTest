package com.example.common_base.base

import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.example.common_base.utils.KeyboardUtil
import com.example.common_base.utils.SystemUtil
import me.jessyan.autosize.AutoSizeCompat
import me.jessyan.autosize.AutoSizeConfig
import timber.log.Timber
import java.lang.ref.WeakReference


abstract class BaseActivity : AppCompatActivity(), OnBackPressedDispatcherOwner {


    abstract fun initView(savedInstanceState: Bundle?)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo pad临时关适配
        if (SystemUtil.isPad()) {
            AutoSizeConfig.getInstance().stop(this)
        }
        initView(savedInstanceState)
        Timber.d("StatusBarHeight: ${BarUtils.getStatusBarHeight()}")
        lifecycle.addObserver(MyLifecycleObservable(javaClass.simpleName))
    }


    override fun onResume() {
        super.onResume()
        BaseApplication.getInstance().currentActivity = WeakReference(this)
    }


    override fun getResources(): Resources {
        if ("main" != Thread.currentThread().name) {
            return super.getResources()
        }
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()))
        AutoSizeCompat.autoConvertDensity(super.getResources(), 375f, true)
        return super.getResources()
    }


    //点击空白区域隐藏键盘
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                KeyboardUtil.hideKeyboardWhenTouchOutEditText(ev, currentFocus)
            }
            else -> {
            }
        }
        return super.dispatchTouchEvent(ev)
    }



//    override fun getOnBackPressedDispatcher(): OnBackPressedDispatcher {
//        OnBackPressedDispatcher(Runnable {
//
//        })
//    }

}