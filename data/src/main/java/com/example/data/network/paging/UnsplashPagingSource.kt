package com.example.data.network.paging

/*
class UnsplashPagingSource(
    private val myApi: MyApi,
    private val query: String
): PagingSource<Int ,UnsplashPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: PAGING_START_PAGE
        return try {
            val response = myApi.searchPhotos(query ,position ,params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == PAGING_START_PAGE) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}*/
