package com.example.littletreetest.usecase

import com.DataResult
import com.example.littletreetest.pages.free.Free
import kotlinx.coroutines.flow.Flow

interface GetFreeUseCase {

    suspend fun invoke(): Flow<DataResult<Free>>

}