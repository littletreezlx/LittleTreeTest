package com.example.littletreetest.base

import android.Manifest
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject


/**
 * author ：lemon
 * createTime：2019/4/18
 * describe：
 */
@AndroidEntryPoint
open class BaseActivity: AppCompatActivity()
    , OnBackPressedDispatcherOwner
{

    @Inject
    lateinit var spUtil: SpUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        //todo pad临时关适配
//        if (isPad()){
//            AutoSizeConfig.getInstance().stop(this)
//        }
//        Timber.d("LittleTreeTest___Lifecycle: ${javaClass.simpleName} ___ onCreate"  )
//        ScreenUtil.activityTitle(this)
        super.onCreate(savedInstanceState)
//        initActivityComponent()
        requestPermissions()
//        EventBus.getDefault().register(this)
        Timber.d(spUtil.toString())
//        if (!NetworkUtils.isConnected()) {
//            showNoNet()
//        }
//
//        val callback = onBackPressedDispatcher.addCallback {
//            Timber.d("onBackPressed___${javaClass.simpleName}")
//            onActivityBackPressed()
//        }
    }



    //必要权限申请
    open fun requestPermissions() {
//        PermissionX.init(this)
//            .permissions(
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.RECORD_AUDIO)
//            .request { allGranted, grantedList, deniedList ->
//                if (grantedList.contains(Manifest.permission.RECORD_AUDIO)) {
////                    (application as App).initWorkerThread()
//                }
//                if (allGranted) {
//                    Timber.d("All permissions are granted")
//                } else {
//                    Timber.d("These permissions are denied: $deniedList")
//                }
//            }
    }

    override fun onPause() {
        super.onPause()
//        (application as App).destroyLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
//        EventBus.getDefault().unregister(this)
        Timber.d("LittleTreeTest___Lifecycle: ${javaClass.simpleName} ___ onDestroy"  )
    }

//    fun showLoading() {
//        if (LoadingDialogTest.getInstance().isAdded) {
//            return
////            childFragmentManager.beginTransaction().remove(loadingDialog).commitNowAllowingStateLoss()
//        }
//        LoadingDialogTest.getInstance().showNow(supportFragmentManager, "loading")
//    }
//
//
//    fun hideLoading() {
//        if (LoadingDialogTest.getInstance().isAdded) {
//            LoadingDialogTest.getInstance().dismissAllowingStateLoss()
//        }
//    }


    private fun initActivityComponent() {
//        mActivityComponent = DaggerActivityComponent.builder()
//            .applicationComponent((getApplication() as App).applicationComponent)
//            .activityModule(ActivityModule(this))
//            .build()
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            android.R.id.home -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                finishAfterTransition()
//            } else {
//                finish()
//            }
//        }
//        return true
//    }

    var  currentFragment : WeakReference<Fragment>? = null
        set(value) {
        field = value
        Timber.d("LittleTreeTest___currentFragment: ${field?.get()}")
        }


    open fun onActivityBackPressed() {
        Timber.d("LittleTreeTest___onBackPressed: ${javaClass.simpleName}")
    }


    override fun onResume() {
        Timber.d("LittleTreeTest___Lifecycle: ${javaClass.simpleName} ___ onResume"  )
        super.onResume()
//        (App.appContext as App).currentActivity = WeakReference(this)
        Timber.d("LittleTreeTest___CurrentActivity: ${javaClass.simpleName}"  )
//        updateSystemUiVisibility()
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        updateSystemUiVisibility()
    }


    protected open fun updateSystemUiVisibility() { // Retrieve if the Immersive mode is enabled or not.
//        val enabled = getSharedPreferences(Util.PREF_NAME, 0).getBoolean(
//            "immersive_mode_enabled", true
//        )
//        if (enabled) enableImmersiveMode() else disableImmersiveMode()
//        enableImmersiveMode()
    }


    private fun enableImmersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }

        //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
//        val window: Window = getWindow()
//        val decorView = window.decorView
//        //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
//        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        decorView.systemUiVisibility = option

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

//    override fun getOnBackPressedDispatcher(): OnBackPressedDispatcher {
//        OnBackPressedDispatcher(Runnable {
//
//        })
//    }
}