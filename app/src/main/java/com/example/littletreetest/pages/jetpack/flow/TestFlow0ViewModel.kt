package com.example.littletreetest.pages.jetpack.flow

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.common_base.ext.showToast
import com.example.littletreetest.pages.free.FreeParentViewModel
import com.mixu.jingtu.common.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class TestFlow0ViewModel @ViewModelInject constructor(
    private val repo: TestFlowRepo,
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {


//    private val _test = MutableLiveData<String>()
//    val test: LiveData<String> = _test


    val flowValue: LiveData<String> = repo.testFlowData.asLiveData()


    fun test(){
        viewModelScope.launch {
            repo.test()
        }
    }


}
