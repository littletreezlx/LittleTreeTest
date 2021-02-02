package com.example.littletreetest.pages.ui.recyclerview

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.doFailure
import com.doSuccess
import com.example.littletreetest.pages.free.FreeAdapter
import com.example.littletreetest.pages.free.FreeParentViewModel
import com.example.littletreetest.pages.free.FreeRepo
import com.example.littletreetest.usecase.GetFreeUseCase
import com.example.littletreetest.usecase.UpdateFreeUseCase
import com.mixu.jingtu.common.base.BaseViewModel
import com.mixu.jingtu.common.ext.showToast
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.concurrent.thread
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask


class RecyclerViewViewModel @ViewModelInject constructor(
    private val repo: RecyclerViewRepo,
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {


    private val _datas = MutableLiveData<MutableList<RvItemDataModel>>()
    val datas: LiveData<MutableList<RvItemDataModel>> = _datas

    private val _onDataAdded = MutableLiveData<RvItemDataModel>()
    val onDataAdded: LiveData<RvItemDataModel> = _onDataAdded

    private val _onDataRemoved = MutableLiveData<RvItemDataModel>()
    val onDataRemoved: LiveData<RvItemDataModel> = _onDataRemoved


    fun startFakeMessageReceiver(){
        viewModelScope.launch {
            timer(period = 1000) {
                addOneData()
            }
        }
    }


    fun getData(testAdapter: TestAdapter) {
        viewModelScope.launch {
            repo.getData().collectLatest {
                it.doSuccess {
                    _datas.value = it
                    testAdapter.setDiffNewData(it)
                }
            }
        }
    }


    fun addOneData() {
        viewModelScope.launch {
            repo.addData().collectLatest {
                it.doSuccess {
//                    _datas.value = it.toMutableList()
                    _onDataAdded.value = it
                }
            }
        }
    }


    fun removeOneData() {
        viewModelScope.launch {
            repo.removeData().collectLatest {
                it.doSuccess {
//                    _datas.value = it
                    _onDataRemoved.value =RvItemDataModel("HHH")
                }
            }
        }
    }


    fun deleteOneData() {

    }


}
