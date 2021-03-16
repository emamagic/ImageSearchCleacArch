package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.common.ApiWrapper
import com.example.domain.repository.IUnsplashRepository
import com.example.data.source.RemoteDataSource
import com.example.domain.entity.Unsplash
import com.example.domain.entity.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepositoryImpl @Inject constructor(private val remote: RemoteDataSource):
    IUnsplashRepository {

    override fun getUnsplashPhotos(query: String): LiveData<PagingData<UnsplashPhoto>> {
        return remote.getSearchResults(query)
    }


}
