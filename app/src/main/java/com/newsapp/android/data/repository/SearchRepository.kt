package com.newsapp.android.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.data.model.topHeadines.TopHeadlinesResponse
import com.newsapp.android.data.paging.SearchPaging
import com.newsapp.android.di.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchRepository constructor(private val networkService: NetworkService) {

    fun getSearchResult(keyword: String, paging: Int, page: Int): Flow<List<Article>> {

        return flow {

            emit(
                networkService.getSearchResult(
                    keyword,
                    paging, page
                )
            )

        }
            .map {
                it.articles
            }

    }

    suspend fun getSearchResultWithoutFlow(
        keyword: String,
        paging: Int,
        page: Int
    ): TopHeadlinesResponse {
        Log.v("testPaging", "$keyword  -- $paging  --  $page")

        return networkService.getSearchResult(
            keyword,
            paging, page
        )

    }

    fun getSearchResultWithPaging(keyword: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {

            SearchPaging(this, keyword)
        }
    ).flow

}