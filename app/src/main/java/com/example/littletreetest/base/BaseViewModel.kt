package com.mixu.jingtu.common.base

import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


open class BaseViewModel : ViewModel() {






//    val loginManager: LoginManager = ComponentHolder.appComponent.loginManager
//
//    val spUtil = ComponentHolder.appComponent.spUtil
//
//    val appVM by lazy {
//        ViewModelProvider(App.appContext as ViewModelStoreOwner).get(AppViewModel::class.java)
//    }


    //异步获取Repo查找的结果，进入方法时使用viewmodelscope产生的协程，执行查找时使用repo管理的协程
//    suspend fun <T> getAsyncResult(
//        isShowLoading: Boolean,
//        getAsyncRepoResult: suspend () -> T?
//    ): T? {
//        var result: T? = null
//        if (NetworkStateListener.networkState == NetworkStateListener.NETWORK_STATE_UNAVAILABLE) {
//            showToast("请检查网络连接")
//            return result
//        }
//
//        coroutineScope {
//            val startTime = System.currentTimeMillis()
////            var loading: LoadingDialog2? = null
//            if (isShowLoading) {
////                (App.appContext as App).currentActivity?.get()?.let {
////                    loading = LoadingDialog2(it)
////                }
////                loading?.show()
//
////                (App.appContext as App).getLoading()?.show()
//
//                (App.appContext as App).showLoading()
//            }
//            Timber.d("getAsyncResult async start")
//            val deferred = async {
//                getAsyncRepoResult.invoke()
//            }
//            result = deferred.await()
//            if (isShowLoading) {
////                loading?.dismiss()
////                (App.appContext as App).getLoading()?.hide()
//
//                (App.appContext as App).destroyLoading()
//            }
//            Timber.d("getAsyncResult async stop,  cost time: ${System.currentTimeMillis() - startTime}")
//        }
//        return result
//    }

}


/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class LiveEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}


fun <T> LiveData<LiveEvent<T>>.runOnce(owner: LifecycleOwner, job: (t: T) -> Unit) {
    this.observe(owner, Observer { event ->
        event.getContentIfNotHandled()?.let { param ->
            job.invoke(param)
        }
    })
}


//一次性操作，用于方法A需要等待方法B执行完成后(或者状态B的情况下)才能执行，主要用于点击操作
fun LiveData<Boolean>.waitTrueThenRunOnce(owner: LifecycleOwner, job: ()-> Unit){
    this.observe(owner, Observer {
        if (it == true){
            this.removeObservers(owner)
            job.invoke()
        }
    })
}


////方法执行后需要将value从true置为false，不然因为ViewModel的状态保存机制会重复调用，用于啥我忘了
//fun MutableLiveData<Boolean>.run(owner: LifecycleOwner, job: ()-> Unit){
//    this.observe(owner, Observer {
//        if (it == true){
//            value = false
//            job.invoke()
//        }
//    })
//}


class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<LiveEvent<T>> {
    override fun onChanged(liveEvent: LiveEvent<T>?) {
        liveEvent?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
