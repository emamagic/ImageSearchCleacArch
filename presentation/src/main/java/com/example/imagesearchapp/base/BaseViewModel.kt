package com.example.imagesearchapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    var loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    inline fun baseViewModelScope(crossinline call: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch {
            loadingState.value = true
            call()
        }
}