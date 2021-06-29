package com.example.littletreetest.pages.jetpack.flow

import com.example.littletreetest.pages.free.FreeLocalStorage
import com.mixu.jingtu.common.base.BaseRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random


@Singleton
class TestFlowRepo @Inject constructor(
    private val local: FreeLocalStorage
) : BaseRepo() {

    init {
    }


    var testData = "0"

    var testFlowData: Flow<String> = flow {
        emit(testData)
    }


    fun test(){
        testData = Random.nextInt(10).toString()
    }



}
