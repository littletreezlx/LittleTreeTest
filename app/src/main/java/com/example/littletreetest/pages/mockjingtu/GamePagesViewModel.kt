package com.example.littletreetest.pages.mockjingtu

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
import com.mixu.jingtu.common.base.LiveEvent
import com.mixu.jingtu.common.ext.showToast
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber


class GamePagesViewModel @ViewModelInject constructor(
    private val repo: FreeRepo,
    @Assisted private val savedState: SavedStateHandle
) : FreeParentViewModel() {


    private val _toShowBottomPopup = MutableLiveData<LiveEvent<Boolean>>()

    val toShowBottomPopup = _toShowBottomPopup

    fun toShowBottomPopup(toShow: Boolean) {
        _toShowBottomPopup.value = LiveEvent(toShow)
    }



    private val _toTabAt = MutableLiveData<LiveEvent<Int>>()

    val toTabAt = _toTabAt

    fun toTabAt(position: Int) {
        _toTabAt.value = LiveEvent(position)
    }


}
