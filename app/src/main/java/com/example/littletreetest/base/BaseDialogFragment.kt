package com.example.littletreetest.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.littletreetest.R

import timber.log.Timber
import java.lang.ref.WeakReference


/**
 * author ：lemon
 * createTime：2019/4/18
 * describe：
 */
abstract class BaseDialogFragment(val dialogType: Int = DIALOG_TYPE_FULL_SCREEN, val width: Int = WindowManager.LayoutParams.WRAP_CONTENT) : DialogFragment(){

    companion object {
        const val DIALOG_TYPE_FULL_SCREEN = 0
        const val DIALOG_TYPE_CENTER_POPUP = 1
        const val DIALOG_TYPE_BOTTOM_POPUP = 2
    }


    private val onKeyListener = object : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
            if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN){
                onKeyDownLog()
                onBackPressed?.run {
                    invoke()
                    return true
                }
            }
            return false
        }
    }


    private fun onKeyDownLog(){
        Timber.d("onBackPressed${javaClass.simpleName}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCreate")
        super.onCreate(savedInstanceState)

//        setStyle(STYLE_NORMAL, R.style.DialogFragment_FullScreen)

        (activity as BaseActivity).currentFragment = WeakReference(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCreateView")
        dialog?.setCancelable(false)
        dialog?.setOnKeyListener(onKeyListener)
        dialog?.window?.run {
            setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            val activitySystemUiVisibility = activity?.window?.decorView?.systemUiVisibility
            decorView.systemUiVisibility = activitySystemUiVisibility!!
            setBackgroundDrawableResource(R.color.transparent)
            decorView.setPadding(0, 0, 0, 0)
            when(dialogType){
                DIALOG_TYPE_FULL_SCREEN ->{
                    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
                }
                DIALOG_TYPE_CENTER_POPUP ->{
                    setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
                    attributes.gravity = Gravity.CENTER
                }
                DIALOG_TYPE_BOTTOM_POPUP ->{
                    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    attributes.gravity = Gravity.BOTTOM
                }
            }
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//            BarUtils.setNavBarColor(this, Color.parseColor("#DC143C"))

//            BarUtils.setNavBarColor(this, Color.parseColor("#FFFFFF"))

//            BarUtils.setNavBarColor(this, Color.parseColor("#000000"))
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            statusBarColor = Color.TRANSPARENT
        }
        dialog?.setOnShowListener {
            dialog?.window?.run {
                clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
                activity?.getSystemService(Context.WINDOW_SERVICE)?.let {
                    val wm = it as WindowManager
                    wm.updateViewLayout(decorView, attributes)

                }
            }
        }
        var view = super.onCreateView(inflater, container, savedInstanceState)
        layoutId?.let {
            view = inflater.inflate(it, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)

    }

    open val layoutId: Int? = null


    open fun initView(view: View, savedInstanceState: Bundle?) {

    }




    override fun onResume() {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onResume")
        super.onResume()
        (activity as BaseActivity).currentFragment = WeakReference(this)
    }


    override fun onPause() {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onDestroy")
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onHiddenChanged")

        super.onHiddenChanged(hidden)
        if(hidden){

        } else {
            (activity as BaseActivity).currentFragment = WeakReference(this)
        }
    }


    open var onBackPressed: (() -> Unit)? = null


    fun logOnBackPressed() {
        Timber.d("onBackPressed: ${javaClass.simpleName}")
    }

    fun back(){
        logOnBackPressed()
        onBackPressed?.invoke()
    }
}
