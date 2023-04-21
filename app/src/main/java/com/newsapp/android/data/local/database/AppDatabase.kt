package com.newsapp.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newsapp.android.data.local.entities.TopHeadlines
import com.newsapp.android.data.local.dao.TopHeadlinesDao

@Database(entities = [TopHeadlines::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topHeadlinesDao(): TopHeadlinesDao

}