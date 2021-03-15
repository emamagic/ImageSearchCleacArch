package com.example.imagesearchapp.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.ApiWrapper
import com.example.domain.entity.UnsplashPhoto
import com.example.domain.intractor.GetUnsplashPhotosUseCase
import com.example.imagesearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val getPhotosUseCase: GetUnsplashPhotosUseCase): BaseViewModel() {

    private val _getPhotos = MutableLiveData<ApiWrapper<List<UnsplashPhoto>>>()
    val getPhotos: LiveData<ApiWrapper<List<UnsplashPhoto>>>
    get() = _getPhotos


    fun getUnsplashPhotos(query: String = "cats") = viewModelScope.launch {
       // loadingState.value = true
        _getPhotos.value = getPhotosUseCase.execute(query)
    }

}