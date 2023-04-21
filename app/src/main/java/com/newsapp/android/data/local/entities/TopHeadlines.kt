package com.newsapp.android.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TopHeadlines")
data class TopHeadlines(


    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String? = "",
    @ColumnInfo val sourceName: String,
    @ColumnInfo val imageUrl: String? = "",
    @ColumnInfo val url: String? = ""
)
