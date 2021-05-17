package com.example.littletreetest.pages.free

import android.app.Activity
import com.DataResult
import com.doFailure
import com.doSuccess
import com.mixu.jingtu.common.base.BaseRepo
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject


@ActivityScoped
class FreeRepo @Inject constructor(
//    @ActivityContext private val activity: FragmentActivity,
//    @ApplicationContext context: Context,
    context: Activity,
    appRepo: AppRepo,
    face: HiltInterface,
    private val local: FreeLocalStorage
) : BaseRepo() {

    init {
        Timber.d(context.toString())
        Timber.d(appRepo.toString())
    }

    suspend fun getFree() = flow {
        val result = local.freeLocal
        if (result != null) {
            emit(DataResult.Success(Free(result.name + "___FromLocal")))
        } else {
            val remote = getFreeRemote()
            remote.doSuccess {
                local.freeLocal = it
                emit(DataResult.Success(it))
            }
            remote.doFailure {
                emit(DataResult.Failure("hhh"))
            }
        }
    }.flowOn(Dispatchers.IO)


    suspend fun updateFree() = flow {
        val remote = updateFreeRemote()
        remote.doSuccess {
            local.freeLocal = it
            emit(DataResult.Success(it))
        }
        remote.doFailure {
            emit(DataResult.Failure("hhh"))
        }
    }.flowOn(Dispatchers.IO)


    private suspend fun getFreeRemote(): DataResult<Free> {
        return DataResult.Success(Free("remote_free"))
    }

    private suspend fun updateFreeRemote(): DataResult<Free> {
        return DataResult.Success(Free("remote_free_updated"))
    }

}


data class Free(var name: String) {

}