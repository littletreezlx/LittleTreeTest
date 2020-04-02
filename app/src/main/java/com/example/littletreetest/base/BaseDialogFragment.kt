package com.example.littletreetest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.littletreetest.R

abstract class BaseDialogFragment(val isFullScreen: Boolean = true) : DialogFragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFullScreen) {
            setStyle(STYLE_NORMAL, R.style.Dialog_FullScreen)
        }
//        (activity as BaseActivity).currentFragment = WeakReference(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun show(manager: FragmentManager, tag: String?) {
//        if (dialog?.window != null && dialog?.window?.decorView != null) {
//            dialog?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        }
        super.show(manager, tag)
    }


    override fun onResume() {
        super.onResume()
//        setBaseEvents()
//        (activity as BaseActivity).currentFragment = WeakReference(this)
    }


    override fun onPause() {
        super.onPause()
//        dialog?.setOnKeyListener(null)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){

        } else {
//            (activity as BaseActivity).currentFragment = WeakReference(this)
        }
    }


    open var onBackPressed: (() -> Unit)? = null


}
