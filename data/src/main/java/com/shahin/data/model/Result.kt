package com.shahin.data.model

sealed class Result<out T>(
    val data: T? = null,
    val message: Throwable? = null
) {
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: Throwable, data: T? = null) : Result<T>(data, message)
}
