package com.example.littletreetest.pages.testthird.smartrefreshlayout

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.mixu.jingtu.common.base.BaseViewModel


class SmartRefreshViewModel @ViewModelInject constructor(
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {

    private val _test = MutableLiveData<String>()


    val test: LiveData<String> = _test


}
