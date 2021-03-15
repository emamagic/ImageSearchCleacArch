package com.example.domain.repository

import com.example.common.ApiWrapper
import com.example.domain.entity.UnsplashPhoto

interface IUnsplashRepository {

    suspend fun getUnsplashPhotos(query: String): ApiWrapper<List<UnsplashPhoto>>

}