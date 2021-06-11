package com.example.common_base.base

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import me.jessyan.autosize.AutoSizeCompat
import me.jessyan.autosize.AutoSizeConfig
import timber.log.Timber
import com.example.utils.SystemUtil



abstract class CommonBaseActivity : AppCompatActivity(), OnBackPressedDispatcherOwner {


    abstract fun initView(savedInstanceState: Bundle?)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo pad临时关适配
        if (SystemUtil.isPad()) {
            AutoSizeConfig.getInstance().stop(this)
        }
        if (!NetworkUtils.isConnected()) {
            showNoNet()
        }
//        val callback = onBackPressedDispatcher.addCallback {
//            Timber.d("onBackPressed___${javaClass.simpleName}")
//            onActivityBackPressed()
//        }
        initView(savedInstanceState)
        Timber.d("StatusBarHeight: ${BarUtils.getStatusBarHeight()}")
        lifecycle.addObserver(MyLifecycleObservable(javaClass.simpleName))
    }


    fun showNoNet() {
        ToastUtils.showShort("请检查网络")
    }



    override fun getResources(): Resources {
        if ("main" != Thread.currentThread().name) {
            return super.getResources()
        }
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()))
        AutoSizeCompat.autoConvertDensity(super.getResources(), 375f, true)
        return super.getResources()
    }


//    override fun getOnBackPressedDispatcher(): OnBackPressedDispatcher {
//        OnBackPressedDispatcher(Runnable {
//
//        })
//    }

}