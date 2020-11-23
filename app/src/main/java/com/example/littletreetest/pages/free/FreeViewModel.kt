package com.example.littletreetest.pages.free

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
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
//    private val getFreeUseCase: GetFreeUseCase,
//    private val updateFreeUseCase: UpdateFreeUseCase,
    @Assisted private val savedState: SavedStateHandle
) : FreeParentViewModel() {


    var testNavGraphViewModels = "0"



    private val SAVED_STATE_KEY = "key"

    private val _test = MutableLiveData<String>()


    val test: LiveData<String> = _test


    val freeAdapter by lazy {
        FreeAdapter(mutableListOf())
    }


    val source0 = MutableLiveData<Int>()

    val source1 = MutableLiveData<Int>()

    val testList = MediatorLiveData<MutableList<String>>().apply {
        value = mutableListOf<String>("1","2","3")

        addSource(source0) {
            value = value?.apply {
                add("4")
            }
        }
        addSource(source1) {
            value = value?.apply {
                removeLast()
            }
        }
    }




}
