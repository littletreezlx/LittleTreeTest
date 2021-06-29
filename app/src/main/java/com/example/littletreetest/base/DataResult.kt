package com.example.littletreetest.base


sealed class DataResult<out T> {
    data class Success<out T>(val value: T) : DataResult<T>()

    data class Failure(val errorMsg: String) : DataResult<Nothing>()
}

inline fun <reified T> DataResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is DataResult.Success) {
        success(value)
    }
}

inline fun <reified T> DataResult<T>.doFailure(failure: (String) -> Unit) {
    if (this is DataResult.Failure) {
        failure(errorMsg)
    }
}
