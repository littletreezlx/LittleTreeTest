package com.example.common_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.ext.BindingLifecycleOwner
import timber.log.Timber


abstract class CommonBaseFragment: Fragment(), BindingLifecycleOwner {


    abstract val layoutId: Int

    var isBeenResumed = false

    private var backCallBack: OnBackPressedCallback? = null

    private var originalMode: Int? = null

    open val keyboardMode: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed?.let {
            backCallBack = requireActivity().onBackPressedDispatcher.addCallback(this) {
                logOnBackPressed()
                it.invoke()
            }
        }
        lifecycle.addObserver(MyLifecycleObservable(javaClass.simpleName))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
    }


    open fun initView(view: View, savedInstanceState: Bundle?) {

    }




    override fun onResume() {
        super.onResume()
        originalMode = activity?.window?.attributes?.softInputMode
        keyboardMode?.let {
            activity?.window?.setSoftInputMode(it)
        }
       isBeenResumed = true
    }


    override fun onPause() {
        super.onPause()
        originalMode?.let {
            activity?.window?.setSoftInputMode(it)
        }
    }



//    override fun onDestroy() {
//        super.onDestroy()
//        Timber.d("Lifecycle: ${javaClass.simpleName}___onDestroy")
//        backCallBack = null
//    }



    override fun onDestroyViewBinding(destroyingBinding: ViewBinding) {
    }


    open var onBackPressed: (() -> Unit)? = null
        set(value) {
            field = value
            backCallBack = requireActivity().onBackPressedDispatcher.addCallback(this) {
                logOnBackPressed()
                field?.invoke()
            }
        }


    fun logOnBackPressed() {
        Timber.d("onBackPressed: ${javaClass.simpleName}")
    }



    fun back() {
        logOnBackPressed()
        onBackPressed?.invoke()
    }


}
