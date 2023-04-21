package com.newsapp.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.newsapp.android.data.local.entities.TopHeadlines
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadlinesDao {

    @Insert
    fun insertAll(topHeadlines: List<TopHeadlines>)

    @Update
    fun update(topHeadlines: TopHeadlines)

    @Delete
    fun delete(topHeadlines: TopHeadlines)

    @Query("delete from TopHeadlines")
    fun deleteAllTopHeadlines(): Int

    @Query("select * from TopHeadlines order by id desc")
    fun getAllTopHeadlines(): Flow<List<TopHeadlines>>

}