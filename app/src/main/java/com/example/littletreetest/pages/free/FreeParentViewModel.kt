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


open class FreeParentViewModel @ViewModelInject constructor() : BaseViewModel() {


    val _testParent = MutableLiveData<String>()
    val testParent: LiveData<String> = _testParent
}
