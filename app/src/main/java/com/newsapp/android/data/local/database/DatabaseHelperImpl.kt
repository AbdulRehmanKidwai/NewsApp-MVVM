package com.newsapp.android.data.local.database

import com.newsapp.android.data.local.entities.TopHeadlines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DatabaseHelperImpl @Inject constructor(private val appDatabase: AppDatabase) :
    DatabaseHelper {

    override fun getTopHeadlines(): Flow<List<TopHeadlines>> {

        return appDatabase.topHeadlinesDao().getAllTopHeadlines()
    }

    override fun deleteAll(): Int {

        return appDatabase.topHeadlinesDao().deleteAllTopHeadlines()
    }

    //Why ot has return type Unit why don't we return nthinh
    override fun insertAll(data: List<TopHeadlines>): Flow<Unit> {

        return flow {
            appDatabase.topHeadlinesDao().insertAll(data)
            emit(Unit)

        }


    }
}