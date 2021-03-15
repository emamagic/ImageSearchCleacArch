package com.example.common

import okhttp3.Headers


sealed class ApiWrapper<T>(
    val data: T? = null,
    val error: ErrorResponse? = null,
    val headers: Headers? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success <T> (data: T ,headers: Headers ,code: Int): ApiWrapper<T>(data ,null ,headers ,null ,code)
    class ApiError<T>(error: ErrorResponse?, headers: Headers): ApiWrapper<T>(null,error,headers ,null)
    class NetworkError<T>(message: String): ApiWrapper<T>(null,null,null ,message)
    class UnknownError<T>(message: String): ApiWrapper<T>(null,null,null ,message)
    class Loading<T>: ApiWrapper<T>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success [data -> $data]"
            is ApiError<*> -> "ApiError [error -> $error]"
            is NetworkError<*> -> "NetworkError [error -> $message]"
            is UnknownError<*> -> "UnknownError [error -> $message]"
            else -> " else "
        }
    }
}

val ApiWrapper<*>.succeeded
    get() = this is ApiWrapper.Success && data != null