package com.newsapp.android.data.repository

import com.newsapp.android.data.local.database.DatabaseHelperImpl
import com.newsapp.android.data.local.entities.TopHeadlines
import com.newsapp.android.data.model.topHeadines.toTopHeadlines
import com.newsapp.android.di.api.NetworkService
import com.newsapp.android.utils.AppConstant.COUNTRY_US
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class TopHeadlinesRepository constructor(
    private val networkService: NetworkService,
    private val databaseHelperImpl: DatabaseHelperImpl
) {

    fun getTopHeadlines(): Flow<List<TopHeadlines>> {

        return flow {
            emit(networkService.getTopHeadlines(COUNTRY_US))
        }
            .map {
                val apiTopHeadlines = it.articles
                val topHeadlines = mutableListOf<TopHeadlines>()

                for (apiTopHeadline in apiTopHeadlines) {
                    topHeadlines.add(apiTopHeadline.toTopHeadlines())
                }

                return@map topHeadlines
            }
            .flatMapConcat { topHeadlines ->

                return@flatMapConcat flow {
                    emit(databaseHelperImpl.deleteAll())
                }
                    .flatMapConcat {
                        return@flatMapConcat databaseHelperImpl.insertAll(topHeadlines)
                    }
                    .flatMapConcat {
                        return@flatMapConcat databaseHelperImpl.getTopHeadlines()
                    }
            }
    }

    fun getTopHeadlinesFromDb() : Flow<List<TopHeadlines>>{
        return databaseHelperImpl.getTopHeadlines()
    }

}