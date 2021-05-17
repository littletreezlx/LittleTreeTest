package com.example.littletreetest.usecase

import com.DataResult
import com.example.littletreetest.pages.free.Free
import com.example.littletreetest.pages.free.FreeRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UpdateFreeUseCaseImpl @Inject constructor(private val freeRepo: FreeRepo) :
    UpdateFreeUseCase {
    override suspend fun invoke(): Flow<DataResult<Free>> {
        return freeRepo.updateFree()
    }
}


class DeleteFreeUseCaseImpl @Inject constructor(private val freeRepo: FreeRepo) :
    DeleteFreeUseCase {
    override suspend fun invoke(): Flow<DataResult<Free>> {
        return freeRepo.updateFree()
    }
}