package com.example.littletreetest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.example.littletreetest.R
import timber.log.Timber
import java.lang.ref.WeakReference
import kotlin.reflect.KClass


abstract class BaseFragment : Fragment() {




    var backCallBack: OnBackPressedCallback? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


//    val onBackPressedCallback =  OnBackPressedCallback() {
//        override fun handleOnBackPressed() {
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCreate")
        super.onCreate(savedInstanceState)
        onBackPressed?.let {
//            backCallBack = requireActivity().onBackPressedDispatcher.addCallback(this) {
////                logOnBackPressed()
//                it.invoke()
//            }
        }
        (activity as BaseActivity).currentFragment = WeakReference(this)
    }

    fun logOnBackPressed() {
        Timber.d("onBackPressed: ${javaClass.simpleName}")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Lifecycle: ${javaClass.simpleName}___onCreateView")
//        var view = super.onCreateView(inflater, container, savedInstanceState)
        var view: View? = null
        layoutId?.let {
            view = inflater.inflate(it, container, false)
        }
//        setViewBinding(view)
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

    override fun onStart() {
        super.onStart()
        Timber.d("Lifecycle: ${javaClass.simpleName}___onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("Lifecycle: ${javaClass.simpleName}___onResume")

        (activity as BaseActivity).currentFragment = WeakReference(this)
    }


    override fun onPause() {
        super.onPause()
        Timber.d("Lifecycle: ${javaClass.simpleName}___onPause")
//        (activity as BaseActivityKT).currentFragment = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("Lifecycle: ${javaClass.simpleName}___onDestroy")
        backCallBack = null
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Timber.d("Lifecycle: ${javaClass.simpleName}___onHiddenChanged")
        if (hidden) {
        } else {
            (activity as BaseActivity).currentFragment = WeakReference(this)
        }
    }


    open var onBackPressed: (() -> Unit)? = null

    fun back(){
        logOnBackPressed()
        onBackPressed?.invoke()
    }
}
