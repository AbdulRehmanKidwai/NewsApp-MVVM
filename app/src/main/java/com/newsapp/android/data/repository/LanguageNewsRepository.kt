package com.newsapp.android.data.repository

import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.data.model.topHeadines.TopHeadlinesResponse
import com.newsapp.android.di.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LanguageNewsRepository constructor(private val networkService: NetworkService) {

    fun getNewsWithLanguage(language: String): Flow<List<Article>> {

        return flow { emit(networkService.getNewsWithLanguageResult(language)) }
            .map { it.articles }

    }

    suspend fun getNewsWithLanguageWithoutFlow(
        language: String
    ): TopHeadlinesResponse {

        return networkService.getNewsWithLanguageResult(language)
    }

    fun getNewsWithCountry(country: String): Flow<List<Article>> {

        return flow { emit(networkService.getNewsWithCountryResult(country)) }
            .map { it.articles }
    }

}