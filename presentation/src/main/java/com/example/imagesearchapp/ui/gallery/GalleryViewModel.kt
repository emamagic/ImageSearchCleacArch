package com.example.imagesearchapp.ui.gallery

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.common.ApiWrapper
import com.example.domain.entity.Unsplash
import com.example.domain.intractor.GetUnsplashPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val getPhotosUseCase: GetUnsplashPhotosUseCase): ViewModel() {

    private val currentQuery = MutableLiveData("cats")

    val photos = currentQuery.switchMap { queryString ->
            getPhotosUseCase.executeImmediate(queryString).cachedIn(viewModelScope)
        }

    fun searchPhotos(query: String){
        currentQuery.value = query
    }

}