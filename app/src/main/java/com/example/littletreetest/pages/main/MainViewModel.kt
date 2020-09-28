package com.example.littletreetest.pages.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.mixu.jingtu.common.base.BaseViewModel
import timber.log.Timber


class MainViewModel @ViewModelInject constructor() : BaseViewModel() {


//    , @Assisted private val savedState: SavedStateHandle

    private val SAVED_STATE_KEY = "key"

    private val _test = MutableLiveData<String>().apply {
//        savedState.get<String>(SAVED_STATE_KEY)?.let {
//            if (it.isNotEmpty()) {
//                value = it
//            }
//        }
    }


    val test: LiveData<String> = _test


    fun test() {
        _test.postValue("LLL")
//        savedState.set(SAVED_STATE_KEY, str)
    }


    override fun onCleared() {
        super.onCleared()
        Timber.d("onClear")
    }
}
