package com.shahin.data.model

sealed class DataResult<out T>(
    val data: T? = null,
    val message: Throwable? = null
) {
    class Loading<T>(data: T? = null) : DataResult<T>(data)
    class Success<T>(data: T) : DataResult<T>(data)
    class Error<T>(message: Throwable, data: T? = null) : DataResult<T>(data, message)
}
