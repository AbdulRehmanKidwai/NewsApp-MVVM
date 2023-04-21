package com.newsapp.android.ui.languages

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.data.repository.LanguageNewsRepository
import com.newsapp.android.ui.base.BaseViewModel
import com.newsapp.android.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageNewsViewModel(private val languageNewsRepository: LanguageNewsRepository) :
    BaseViewModel() {

    private val _articleList = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val articleList: StateFlow<UiState<List<Article>>> = _articleList

    fun fetchNewsWithLanguage(language: String) {

        viewModelScope.launch {

            languageNewsRepository.getNewsWithLanguage(language)
                .catch {
                    _articleList.value = UiState.Error(this.toString())
                }
                .collect {
                    _articleList.value = UiState.Success(it)
                }

        }
    }

    fun fetchNewsWithCountry(country: String) {

        Log.v("kjsjqs", country);

        viewModelScope.launch {

            languageNewsRepository.getNewsWithCountry(country)
                .catch {
                    _articleList.value = UiState.Error(this.toString())
                }
                .collect {
                    _articleList.value = UiState.Success(it)
                }

        }
    }


}