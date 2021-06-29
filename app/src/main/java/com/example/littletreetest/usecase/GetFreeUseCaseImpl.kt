package com.example.littletreetest.usecase

import com.example.littletreetest.base.DataResult
import com.example.littletreetest.pages.free.Free
import com.example.littletreetest.pages.free.FreeRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFreeUseCaseImpl @Inject constructor(private val freeRepo: FreeRepo) : GetFreeUseCase {

    override suspend fun invoke(): Flow<DataResult<Free>> {
        return freeRepo.getFree()
    }

}