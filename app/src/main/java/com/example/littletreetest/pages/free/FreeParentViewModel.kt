package com.example.littletreetest.pages.free

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mixu.jingtu.common.base.BaseViewModel


open class FreeParentViewModel @ViewModelInject constructor() : BaseViewModel() {


    val _testParent = MutableLiveData<String>()
    val testParent: LiveData<String> = _testParent
}
