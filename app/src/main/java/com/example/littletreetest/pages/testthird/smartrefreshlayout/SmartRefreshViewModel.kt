package com.example.littletreetest.pages.testthird.smartrefreshlayout

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.mixu.jingtu.common.base.BaseViewModel
import com.tencent.bugly.proguard.t
import timber.log.Timber


class SmartRefreshViewModel @ViewModelInject constructor(
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {


    val refreshing: LogLiveData<Boolean> = LogLiveData()

}


class LogLiveData<T> : MutableLiveData<T>() {

    override fun setValue(value: T) {
        super.setValue(value)
        Timber.d("$value")
    }

}