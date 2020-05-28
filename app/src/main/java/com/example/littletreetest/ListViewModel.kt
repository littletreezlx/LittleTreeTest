package com.example.littletreetest

import androidx.lifecycle.*
import kotlinx.coroutines.delay

class ListViewModel : ViewModel() {

    private val _showToast = MutableLiveData<Event<String>>()


    val showToast: LiveData<Event<String>>
        get() = _showToast


    fun clickShowToast(str: String) {
        _showToast.value = Event(str)
    }


    val _AllStr = MutableLiveData<String>()

//    val subStr = _AllStr.map { it ->
//        it + "sub"
//    }

//    val subStr: LiveData<String> = _AllStr.switchMap {
//        liveData {
//            delay(1000)
//            emit(it + "sub")
//        }
//    }


    val subStr: LiveData<String> = _AllStr.switchMap {
//        liveData {
//            emit(it + "sub")
//        }
//
        MutableLiveData(it + "sub")
    }

    fun updateStr(str: String) {
        _AllStr.value = str
    }


}


fun <T> LiveData<Event<T>>.runOnce(owner: LifecycleOwner, job: (t: T) -> Unit) {
    this.observe(owner, Observer { event ->
        event.getContentIfNotHandled()?.let { param ->
            job.invoke(param)
        }
    })
}


class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
