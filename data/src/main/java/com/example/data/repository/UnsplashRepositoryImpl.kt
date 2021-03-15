package com.example.data.repository

import com.example.common.ApiWrapper
import com.example.domain.repository.IUnsplashRepository
import com.example.data.source.RemoteDataSource
import com.example.domain.entity.UnsplashPhoto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepositoryImpl @Inject constructor(private val remote: RemoteDataSource):
    IUnsplashRepository {

    override suspend fun getUnsplashPhotos(query: String): ApiWrapper<List<UnsplashPhoto>> {
        return remote.getSearchResults(query)
    }


}
