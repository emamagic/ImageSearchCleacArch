package com.example.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.data.network.MyApi
import com.example.data.network.paging.UnsplashPagingSource
import com.example.data.network.safe.SafeApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val myApi: MyApi
): SafeApi() {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(myApi ,query) }
        ).liveData


}
