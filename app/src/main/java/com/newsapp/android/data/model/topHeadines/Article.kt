package com.newsapp.android.data.model.topHeadines

import com.google.gson.annotations.SerializedName
import com.newsapp.android.data.local.entities.TopHeadlines

data class Article(

    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String? = "",
    @SerializedName("source")
    val source: Source
)

fun Article.toTopHeadlines(): TopHeadlines {

return TopHeadlines(title = this.title, description = this.description, sourceName = this.source.name,
imageUrl = this.imageUrl, url = this.imageUrl)
}
