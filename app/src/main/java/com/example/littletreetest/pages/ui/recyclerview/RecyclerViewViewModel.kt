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


class RecyclerViewViewModel @ViewModelInject constructor(
    private val repo: FreeRepo,
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {


    private val _datas = MutableLiveData<MutableList<RvItemDataModel>>()
    val datas: LiveData<MutableList<RvItemDataModel>> = _datas


    fun getData() {
        val length = 20
        val originDatas = MutableList<RvItemDataModel>(length) {
            RvItemDataModel("")
        }
        for (i in 0 until  length) {
            originDatas[i].content = i.toString()
        }
        _datas.value = originDatas
    }

}
