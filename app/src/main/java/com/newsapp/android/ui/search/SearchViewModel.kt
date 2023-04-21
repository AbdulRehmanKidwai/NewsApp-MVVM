package com.newsapp.android.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.data.repository.SearchRepository
import com.newsapp.android.ui.base.BaseViewModel
import com.newsapp.android.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {

    //Ask$ explain resource again
    private val _searchList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val searchList: StateFlow<Resource<List<Article>>> = _searchList

    //Ask$ why this
    fun fetchResult(string: String) =
        searchRepository.getSearchResultWithPaging(string).cachedIn(viewModelScope)

}