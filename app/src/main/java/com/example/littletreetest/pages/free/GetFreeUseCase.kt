package com.example.littletreetest.pages.free

import com.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

interface GetFreeUseCase {


    suspend fun invoke(): Flow<DataResult<Free>>

}