package com.example.littletreetest.pages.free

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.doFailure
import com.doSuccess
import com.mixu.jingtu.common.base.BaseViewModel
import com.mixu.jingtu.common.ext.showToast
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


class FreeViewModel @ViewModelInject constructor(
    private val getFreeUseCase: GetFreeUseCase,
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {

    private val SAVED_STATE_KEY = "key"

    private val _test = MutableLiveData<String>().apply {
        savedState.get<String>(SAVED_STATE_KEY)?.let {
            if (it.isNotEmpty()) {
                value = it
            }
        }
    }


    val test: LiveData<String> = _test


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
                    }
                    it.doFailure { errorMsg ->
                        showToast("搜索失败: $errorMsg")
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
