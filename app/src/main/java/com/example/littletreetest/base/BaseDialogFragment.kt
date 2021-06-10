package com.example.littletreetest.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import com.example.littletreetest.R
import com.mixu.jingtu.common.ext.dp
import timber.log.Timber
import java.lang.ref.WeakReference


abstract class BaseDialogFragment : DialogFragment() {

    companion object {
        const val DIALOG_TYPE_FULL_SCREEN = 0
        const val DIALOG_TYPE_CENTER_POPUP = 1
        const val DIALOG_TYPE_BOTTOM_POPUP = 2
    }

    abstract val dialogType: Int

    open val dialogWidth: Int = WindowManager.LayoutParams.WRAP_CONTENT

    open val maxDialogHeight: Int = 600.dp

    open val layoutId: Int? = null

    val spUtil = SpUtil.getInstance()

    private var originalMode: Int? = null

    open val keyboardMode: Int? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireActivity(), theme) {
            override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
                when (ev.action) {
                    MotionEvent.ACTION_DOWN -> {
                        KeyboardUtils.hideSoftInput(requireActivity())
                    }
                    else -> {
                    }
                }
                return super.dispatchTouchEvent(ev)
            }
        }
    }


    private val onKeyListener = object : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
            if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
                onKeyDownLog()
                onBackPressed?.run {
                    invoke()
                    return true
                }
            }
            return false
        }
    }


    private fun onKeyDownLog() {
        Timber.d("onBackPressed${javaClass.simpleName}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCreate")
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFragment_FullScreen)
        activity?.let {
            (it as BaseActivity).currentFragment = WeakReference(this)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCreateView")
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setOnKeyListener(onKeyListener)
        dialog?.window?.run {
            setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
            val activitySystemUiVisibility = activity?.window?.decorView?.systemUiVisibility
            decorView.systemUiVisibility = activitySystemUiVisibility!!
            setBackgroundDrawableResource(R.color.transparent)
            decorView.setPadding(0, 0, 0, 0)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        updateDialogType()
        //解决沉浸全屏问题
        dialog?.setOnShowListener {
            dialog?.window?.run {
                clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
                activity?.getSystemService(Context.WINDOW_SERVICE)?.let {
                    val wm = it as WindowManager
                    wm.updateViewLayout(decorView, attributes)
                }
            }
        }
        //简单写布局
        var view = super.onCreateView(inflater, container, savedInstanceState)
        layoutId?.let {
            view = inflater.inflate(it, container, false)
        }
        //中间弹出的窗口做最大高度限制
        if (dialogType == DIALOG_TYPE_CENTER_POPUP) {
            view?.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                v?.let {
                    Timber.d("onLayoutChange: dialogHeight: ${it.height}")
                    Timber.d("onLayoutChange: MaxDialogHeight: ${maxDialogHeight}")
                    Timber.d("onLayoutChange: AppHeight: ${ScreenUtils.getAppScreenHeight()}")
                    if (it.height > maxDialogHeight) {
                        if (dialogType == DIALOG_TYPE_CENTER_POPUP) {
                            dialog?.window?.setLayout(
                                dialogWidth,
                                maxDialogHeight
                            )
                        }
                    }
                }
            }
        }
        return view
    }


    private fun updateDialogType() {
        dialog?.window?.run {
            when (dialogType) {
                DIALOG_TYPE_FULL_SCREEN -> {
                    setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                    )
                }
                DIALOG_TYPE_CENTER_POPUP -> {
                    attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
                    attributes.width = dialogWidth
                    attributes.gravity = Gravity.CENTER
                }
                DIALOG_TYPE_BOTTOM_POPUP -> {
                    setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                    )
                    attributes.gravity = Gravity.BOTTOM
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)

    }


    open fun initView(view: View, savedInstanceState: Bundle?) {

    }


    override fun onStart() {
        super.onStart()
        Timber.d("Lifecycle: ${javaClass.simpleName}___onStart")
        originalMode = activity?.window?.attributes?.softInputMode
        keyboardMode?.let {
            activity?.window?.setSoftInputMode(it)
        }
    }


    override fun onResume() {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onResume")
        super.onResume()
        activity?.let {
            (it as BaseActivity).currentFragment = WeakReference(this)
        }
    }


    override fun onPause() {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onPause")
        super.onPause()
    }


    override fun onStop() {
        super.onStop()
        Timber.d("Lifecycle: ${javaClass.simpleName}___onStop")
        originalMode?.let {
            activity?.window?.setSoftInputMode(it)
        }
    }


    override fun onDestroy() {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onDestroy")
        super.onDestroy()
        onDismissFunction?.invoke()
    }


    override fun onHiddenChanged(hidden: Boolean) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onHiddenChanged")
        super.onHiddenChanged(hidden)
        if (hidden) {

        } else {
            activity?.let {
                (it as BaseActivity).currentFragment = WeakReference(this)
            }
        }
    }


    open var onBackPressed: (() -> Unit)? = null


    fun logOnBackPressed() {
        Timber.d("onBackPressed: ${javaClass.simpleName}")
    }


    fun show(fragmentManager: FragmentManager) {
        this.show(fragmentManager, this.javaClass.simpleName)
//        Timber.d("dialog: ${dialog}")
//        dialog?.setOnDismissListener {
//            Timber.d("dialog,ondismiss")
//            onDismissFunction?.invoke()
//        }
    }


    //给外界调用，DialogFragment结束后调用
    //因为很多页面需要覆盖在其上面的DialogFragment结束后刷新数据，但是又不给onResume调用，只能这样了
    open var onDismissFunction: (() -> Unit)? = null


    override fun onDismiss(dialog: DialogInterface) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onDismiss")
        super.onDismiss(dialog)
//        onDismissFunction?.invoke()
    }

    override fun onCancel(dialog: DialogInterface) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCancel")
        super.onCancel(dialog)
    }

    fun back() {
        logOnBackPressed()
        onBackPressed?.invoke()
    }


    fun setOnDismissListener(onDismissFunction: () -> Unit) {
        this.onDismissFunction = onDismissFunction
    }


}
