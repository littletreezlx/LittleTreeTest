package com.example.littletreetest.pages.free

import com.DataResult
import com.doFailure
import com.doSuccess
import com.mixu.jingtu.common.ext.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class GetFreeUseCaseImpl @Inject constructor(private val freeRepo: FreeRepo): GetFreeUseCase {

    override suspend fun invoke(): Flow<DataResult<Free>> {
        return freeRepo.getFree()
    }

}