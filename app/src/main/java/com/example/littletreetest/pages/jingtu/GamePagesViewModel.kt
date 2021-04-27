package com.example.littletreetest.pages.jingtu

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.littletreetest.pages.free.FreeParentViewModel
import com.example.littletreetest.pages.free.FreeRepo
import com.mixu.jingtu.common.base.LiveEvent


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
