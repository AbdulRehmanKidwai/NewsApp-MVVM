package com.newsapp.android.di.api

import com.newsapp.android.data.model.sources.SourcesResponse
import com.newsapp.android.data.model.topHeadines.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesResponse


    @GET("top-headlines")
    suspend fun getNewsWithLanguageResult(
        @Query("language") language: String
    ): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsWithCountryResult(@Query("country") country: String): TopHeadlinesResponse

}