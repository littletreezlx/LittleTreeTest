package com.example.littletreetest.pages.free

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.mixu.jingtu.common.base.BaseViewModel

class FreeViewModel(private val savedState: SavedStateHandle) : BaseViewModel() {

    private val SAVED_STATE_KEY = "key"

    private val _test = MutableLiveData<String>().apply {
        savedState.get<String>(SAVED_STATE_KEY)?.let {
            if (it.isNotEmpty()) {
                value = it
            }
        }
    }


    val test: LiveData<String> = _test


    fun test() {
        val str = "LLL"
        _test.postValue("LLL")
        savedState.set(SAVED_STATE_KEY, str)
    }


}
