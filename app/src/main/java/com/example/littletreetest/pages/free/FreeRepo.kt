package com.example.littletreetest.pages.free

import com.DataResult
import com.doSuccess
import com.mixu.jingtu.common.base.BaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FreeRepo @Inject constructor(private val local: FreeLocalStorage) : BaseRepo() {


    suspend fun getFree() = flow {
//        var result = local.freeLocal
        val remote = getFreeRemote()
        if (local.freeLocal.name == "null") {
            remote.doSuccess {
                local.freeLocal = Free("local_free")
            }
            remote.doSuccess {
                emit(DataResult.Failure("hhh"))
            }
        }
//        emit(DataResult.Success(result))
    }.flowOn(Dispatchers.IO)


    private suspend fun getFreeRemote(): DataResult<Free> {
        return DataResult.Success(Free("remote_free"))
    }

}


data class Free(var name: String) {

}