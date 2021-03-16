package com.example.domain.intractor

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.common.ApiWrapper
import com.example.domain.entity.Unsplash
import com.example.domain.entity.UnsplashPhoto
import com.example.domain.repository.IUnsplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUnsplashPhotosUseCase @Inject constructor(private val repository: IUnsplashRepository): UseCaseWithParamsImmediate<String , LiveData<PagingData<UnsplashPhoto>>>() {

    override fun buildUseCaseImmediate(params: String): LiveData<PagingData<UnsplashPhoto>> {
        return repository.getUnsplashPhotos(params)
    }


}