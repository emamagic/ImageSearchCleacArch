package com.example.imagesearchapp.ui.gallery

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.common.ApiWrapper
import com.example.domain.entity.Unsplash
import com.example.domain.intractor.GetUnsplashPhotosUseCase
import com.example.imagesearchapp.util.Constance.CURRENT_QUERY
import com.example.imagesearchapp.util.Constance.DEFAULT_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getPhotosUseCase: GetUnsplashPhotosUseCase,
    private val state: SavedStateHandle
    ): ViewModel() {

   // private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    private val currentQuery = state.getLiveData(CURRENT_QUERY ,DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
            getPhotosUseCase.executeImmediate(queryString).cachedIn(viewModelScope)
        }

    fun searchPhotos(query: String){
        currentQuery.value = query
    }

}