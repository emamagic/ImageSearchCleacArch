package com.example.imagesearchapp.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.ApiWrapper
import com.example.domain.entity.UnsplashPhoto
import com.example.domain.intractor.GetUnsplashPhotosUseCase
import com.example.imagesearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val getPhotosUseCase: GetUnsplashPhotosUseCase): BaseViewModel() {

    private val _getPhotos = MutableLiveData<ApiWrapper<List<UnsplashPhoto>>>()
    val getPhotos: LiveData<ApiWrapper<List<UnsplashPhoto>>>
    get() = _getPhotos

    fun getUnsplashPhotos(query: String = "cats") = baseViewModelScope {
        _getPhotos.value = getPhotosUseCase.execute(query)
    }

}