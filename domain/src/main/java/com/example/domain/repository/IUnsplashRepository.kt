package com.example.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.domain.entity.UnsplashPhoto
import kotlinx.coroutines.flow.Flow

interface IUnsplashRepository {

    fun getUnsplashPhotos(query: String): LiveData<PagingData<UnsplashPhoto>>

}