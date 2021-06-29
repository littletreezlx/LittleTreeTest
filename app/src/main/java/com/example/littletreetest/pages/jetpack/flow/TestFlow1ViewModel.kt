package com.example.littletreetest.pages.jetpack.flow

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.example.littletreetest.pages.free.FreeParentViewModel
import com.mixu.jingtu.common.base.BaseViewModel


class TestFlow1ViewModel @ViewModelInject constructor(
    private val repo: TestFlowRepo,
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {


//    private val _test = MutableLiveData<String>()
//    val test: LiveData<String> = _test

    val flowValue: LiveData<String> = repo.testFlowData.asLiveData()


    fun test(){
        repo.test()
    }

}
