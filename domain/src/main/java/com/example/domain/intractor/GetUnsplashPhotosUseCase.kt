package com.example.domain.intractor

import com.example.common.ApiWrapper
import com.example.domain.entity.UnsplashPhoto
import com.example.domain.repository.IUnsplashRepository
import javax.inject.Inject

class GetUnsplashPhotosUseCase @Inject constructor(private val repository: IUnsplashRepository): UseCaseWithParams<String ,ApiWrapper<List<UnsplashPhoto>>>() {

    override suspend fun buildUseCase(params: String): ApiWrapper<List<UnsplashPhoto>> {
        return repository.getUnsplashPhotos(params)
    }
}