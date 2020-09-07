package com.example.littletreetest.pages.free

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class FreeViewModel : ViewModel() {

    val repo = FreeRepo.getInstance()

    private val _test = MutableLiveData<Free>().apply {
        value = repo.free
    }

    val test: LiveData<Free> = _test



    fun test(){
//        repo.free.name = "new"

        _test.value = Free("new")

        Timber.d(repo.free.toString())

//        _test.postValue("LLL")
    }
}
