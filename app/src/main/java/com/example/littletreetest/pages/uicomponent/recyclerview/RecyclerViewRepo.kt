package com.example.littletreetest.pages.uicomponent.recyclerview

import com.example.littletreetest.base.DataResult
import com.mixu.jingtu.common.base.BaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecyclerViewRepo @Inject constructor(
) : BaseRepo() {

    private var localData: MutableList<RvItemDataModel> = mutableListOf()


    suspend fun getData() = flow {
        val result = localData
        if (result.isNotEmpty()) {
            emit(DataResult.Success(localData))
        } else {
            localData = createSeqList()
            emit(DataResult.Success(localData))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun addData() = flow {
        val newData = RvItemDataModel(localData.size.toString())
        localData.add(newData)
        emit(DataResult.Success(newData))
    }.flowOn(Dispatchers.IO)


    suspend fun removeData() = flow {
        localData.removeAt(localData.size - 1)
        emit(DataResult.Success(localData))
    }.flowOn(Dispatchers.IO)


    suspend fun removeHistory() = flow {
        Timber.d(localData.toString())
        localData.subList(0, 10).clear()
        Timber.d(localData.toString())
        emit(DataResult.Success(localData))
    }.flowOn(Dispatchers.IO)


    private fun createSeqList(): MutableList<RvItemDataModel> {
        val length = 30
        val originDatas = MutableList<RvItemDataModel>(length) {
            RvItemDataModel("")
        }
        for (i in 0 until length) {
            originDatas[i].content = i.toString()
        }
        return originDatas
    }


}

