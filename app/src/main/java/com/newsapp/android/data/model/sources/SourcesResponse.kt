package com.newsapp.android.data.model.sources

import com.newsapp.android.data.model.sources.Source

data class SourcesResponse(
    val sources: List<Source>,
    val status: String
)