package com.example.imagesearchapp.util

import java.lang.Exception

sealed class LocalWrapper<out R>{
    data class Success<out T>(val data: T): LocalWrapper<T>()
    data class Error(val exception: Exception): LocalWrapper<Nothing>()
    object Loading: LocalWrapper<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success [data=$data]"
            is Error -> "Error[exception=$exception"
            Loading -> "Loading"
        }
    }
}

val LocalWrapper<*>.succeeded
    get() = this is LocalWrapper.Success && data != null