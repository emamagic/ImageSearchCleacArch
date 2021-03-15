package com.example.imagesearchapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearchapp.util.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    var loadingState: MutableLiveData<LoadingState> = MutableLiveData()

    inline fun baseViewModelScope(crossinline call: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch {
            loadingState.value = LoadingState(true)
            call()
        }
}