package com.example.data.network

import com.example.data.util.Constants.ACCESS_KEY
import com.example.domain.entity.Unsplash
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MyApi {

    @Headers("Accept-Version: v1" ,"Authorization: Client-ID $ACCESS_KEY")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Unsplash

}