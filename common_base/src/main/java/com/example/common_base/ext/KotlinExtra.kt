package com.example.common_base.ext

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import java.io.Serializable
import java.text.NumberFormat
import java.util.concurrent.Executors
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.system.exitProcess


private val IO_EXECUTOR = Executors.newSingleThreadExecutor()


fun runOnIOThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}


fun EditText.getTrimedString(): String {
    if (text.isBlank()) {
        return ""
    } else {
        return text.trim().toString()
    }
}


fun EditText.getString(): String {
    if (text.isBlank()) {
        return ""
    } else {
        return text.toString()
    }
}

fun EditText.getInt(): Int {
    if (text.isBlank()) {
        return 0
    } else {
        return text.trim().toString().toInt()
    }
}

fun TextView.getTrimedString(): String {
    if (text.isBlank()) {
        return ""
    } else {
        return text.trim().toString()
    }
}





fun Fragment.setArguments(vararg arguments: Pair<String, Any>) {
    val bundle = Bundle()
    for (argument in arguments) {
        val key = argument.first
        when (val value = argument.second) {
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)
            is Short -> bundle.putShort(key, value)
            is Float -> bundle.putFloat(key, value)
            is Double -> bundle.putDouble(key, value)
            is Boolean -> bundle.putBoolean(key, value)
            is String -> bundle.putString(key, value)
            is Serializable -> bundle.putSerializable(key, value)
        }
    }
    this.arguments = bundle
}


fun Int.toFormatedMoney(): String {
    val instance = NumberFormat.getNumberInstance()
    return instance.format(this)
}

//fun DialogFragment.show(fragmentManager: FragmentManager) {
//    this.show(fragmentManager, this.javaClass.simpleName)
//}


//fun Fragment.startGallery(successFun: (Uri)->Unit){
//    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            result.data?.data?.let {
//                successFun(it)
//            }
//        }
//    }.launch(IntentUtils.getGalleryIntent())
//}

//fun Collection<E>.removeIf(filter: Predicate<in E?>): Boolean {
//    Objects.requireNonNull(filter)
//    var removed = false
//    val each: Iterator<E> = iterator()
//    while (each.hasNext()) {
//        if (filter.test(each.next())) {
//            each.remove()
//            removed = true
//        }
//    }
//    return removed
//}


//inline fun <reified VB : ViewBinding> Fragment.viewBinding() = lazy {
//    VB::class.java.getMethod("bind", View::class.java)
//        .invoke(this, view) as VB
//}

inline fun <reified VB : ViewBinding> Fragment.viewBinding() = FragmentBindingDelegate<VB> { requireView().bind() }

inline fun <reified VB : ViewBinding> View.bind() =
    VB::class.java.getMethod("bind", View::class.java).invoke(null, this) as VB

class FragmentBindingDelegate<VB : ViewBinding>(private val block: () -> VB) :
    ReadOnlyProperty<Fragment, VB> {
    private var binding: VB? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (binding == null) {
            binding = block().also {
                if (it is ViewDataBinding) it.lifecycleOwner = thisRef.viewLifecycleOwner
            }
            thisRef.doOnDestroyView {
                if (thisRef is BindingLifecycleOwner) thisRef.onDestroyViewBinding(binding!!)
                binding = null
            }
        }
        return binding!!
    }
}


interface BindingLifecycleOwner {
    fun onDestroyViewBinding(destroyingBinding: ViewBinding)
}


inline fun Fragment.doOnDestroyView(crossinline block: () -> Unit) =
    viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroyView() {
            block.invoke()
        }
    })





private var firstTime = 0L

fun Fragment.onActivityBackPressed() {
    val secondTime = System.currentTimeMillis()
    if (secondTime - firstTime > 1500) {
        showToast("再按一次退出程序")
        firstTime = secondTime
    } else {
        activity?.finish()
        exitProcess(0)
    }
}






inline fun <reified VM : ViewModel> Fragment.activityViewModels(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
) = createViewModelLazy(VM::class, { requireActivity().viewModelStore },
    factoryProducer ?: { requireActivity().defaultViewModelProviderFactory })



fun <VM : ViewModel> Fragment.createViewModelLazy(
    viewModelClass: KClass<VM>,
    storeProducer: () -> ViewModelStore,
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(viewModelClass, storeProducer, factoryPromise)
}