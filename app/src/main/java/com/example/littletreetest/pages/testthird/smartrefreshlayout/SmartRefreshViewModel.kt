package com.example.littletreetest.pages.testthird.smartrefreshlayout

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


class SmartRefreshViewModel @ViewModelInject constructor(
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel() {

    private val _test = MutableLiveData<String>()


    val test: LiveData<String> = _test


}
