package com.newsapp.android.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.data.repository.SearchRepository

class SearchPaging(private val searchRepository: SearchRepository, private val keyword: String) :
    PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {

        Log.v("testPaging", "getRefreshKey")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        Log.v("testPaging", "load")

        try {
            val position = if (params.key == null) 1 else params.key
            Log.v("testPaging99", position.toString())

            val response = searchRepository.getSearchResultWithoutFlow(
                keyword,
                10,
                (if (params.key == null) 1 else params.key)!!//Ask$ remove !! and solve error
            )
            Log.v("testPaging1", (if (position == 1) null else position?.minus(1)).toString())
            Log.v(
                "testPaging2",
                (if (position == response.totalResults) null else position?.plus(1)).toString()
            )

            return LoadResult.Page(
                data = response.articles,
                prevKey = if (position == 1) null else position?.minus(1),
                nextKey = if (position == response.totalResults) null else position?.plus(1)
            )


        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}