package com.example.littletreetest.pages.free

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.doFailure
import com.doSuccess
import com.example.littletreetest.usecase.GetFreeUseCase
import com.example.littletreetest.usecase.UpdateFreeUseCase
import com.mixu.jingtu.common.base.BaseViewModel
import com.mixu.jingtu.common.ext.showToast
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class FreeViewModel @ViewModelInject constructor(
    private val getFreeUseCase: GetFreeUseCase,
    private val updateFreeUseCase: UpdateFreeUseCase,
    @Assisted private val savedState: SavedStateHandle
) : FreeParentViewModel() {

    private val SAVED_STATE_KEY = "key"

    private val _test = MutableLiveData<String>()
//        .apply {
//        savedState.get<String>(SAVED_STATE_KEY)?.let {
//            if (it.isNotEmpty()) {
//                value = it
//            }
//        }
//    }

    val test: LiveData<String> = _test


    val freeAdapter by lazy {
        FreeAdapter(mutableListOf())
    }

    val testList = MutableLiveData<MutableList<String>>().apply {
        value = mutableListOf<String>("1","2","3")
    }


    fun updateParentText(){
        _testParent.value = "hahaha"
    }


    fun getFree() {
        viewModelScope.launch {
            getFreeUseCase.invoke()
                .onStart {
                }
                .catch {
                    showToast("搜索失败: catch")
                }
                .onCompletion {

                }
                .collectLatest {
                    it.doSuccess {
                        _test.value = it.name
                        showToast("doSuccess: ${it.name}___${Thread.currentThread().name}")
                    }
                    it.doFailure { errorMsg ->
                    }
                }
        }
    }


    fun updateFree() {
        viewModelScope.launch {
            updateFreeUseCase.invoke()
                .onStart {
                }
                .catch {
                    showToast("失败: catch")
                }
                .onCompletion {

                }
                .collectLatest {
                    it.doSuccess {
                        _test.value = it.name
                        showToast("doSuccess: ${it.name}___${Thread.currentThread().name}")
                    }
                    it.doFailure { errorMsg ->
                    }
                }
        }

    }


//    fun test() {
//        val str = "LLL"
//        _test.postValue("LLL")
//        savedState.set(SAVED_STATE_KEY, str)
//    }


}
