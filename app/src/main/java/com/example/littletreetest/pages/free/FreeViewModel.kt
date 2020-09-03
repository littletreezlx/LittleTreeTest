package com.example.littletreetest.pages.free

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FreeViewModel : ViewModel() {


    private val _test = MutableLiveData<String>()

    val test: LiveData<String> = _test



    fun test(){

        _test.postValue("LLL")
    }
}
