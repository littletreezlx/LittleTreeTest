package com.example.littletreetest.usecase

import com.example.littletreetest.base.DataResult
import com.example.littletreetest.pages.free.Free
import kotlinx.coroutines.flow.Flow

interface UpdateFreeUseCase {

    suspend fun invoke(): Flow<DataResult<Free>>

}


interface DeleteFreeUseCase {

    suspend fun invoke(): Flow<DataResult<Free>>

}