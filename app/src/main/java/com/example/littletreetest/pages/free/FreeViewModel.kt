package com.example.littletreetest.pages.free

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import timber.log.Timber


class FreeViewModel @ViewModelInject constructor(
//    private val getFreeUseCase: GetFreeUseCase,
    private val repo: FreeRepo,
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
        value = mutableListOf<String>("1", "2", "3")

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


    fun test() {
        Timber.d(repo.toString())
    }

}
