package com.example.imagesearchapp.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.ApiWrapper
import com.example.domain.entity.UnsplashPhoto
import com.example.domain.intractor.GetUnsplashPhotosUseCase
import com.example.imagesearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val getPhotosUseCase: GetUnsplashPhotosUseCase): ViewModel() {

    private val _getPhotos = MutableLiveData<ApiWrapper<List<UnsplashPhoto>>>()
    val getPhotos: LiveData<ApiWrapper<List<UnsplashPhoto>>>
    get() = _getPhotos

    fun getUnsplashPhotos(query: String = "cats") = viewModelScope.launch {
        _getPhotos.value = ApiWrapper.Loading()
        delay(2000)
        _getPhotos.value = getPhotosUseCase.execute(query)
    }

}