package com.example.latestnews.network.rx

const val NON_HTTP_EXCEPTION = -1

sealed class ResponseState<out T> {

    object Loading : ResponseState<Nothing>()

    data class Success<T>(val response: T) : ResponseState<T>()

    data class Error(
        val httpErrorCode: Int = NON_HTTP_EXCEPTION,
    ) : ResponseState<Nothing>()
}
