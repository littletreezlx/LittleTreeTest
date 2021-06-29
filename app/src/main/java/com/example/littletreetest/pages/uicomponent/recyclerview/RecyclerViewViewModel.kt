package com.example.littletreetest.pages.uicomponent.recyclerview

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.littletreetest.base.doSuccess
import com.mixu.jingtu.common.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.concurrent.timer


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

    private val _onHistoryRemoved = MutableLiveData<Int>()
    val onHistoryRemoved: LiveData<Int> = _onHistoryRemoved


    fun startFakeMessageReceiver() {
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
                    _onDataRemoved.value = RvItemDataModel("HHH")
                }
            }
        }
    }


    fun removeHistory() {
        viewModelScope.launch {
            repo.removeHistory().collectLatest {
                it.doSuccess {
                    _onHistoryRemoved.value = 10
                }
            }
        }
    }


    fun deleteOneData() {

    }


}
