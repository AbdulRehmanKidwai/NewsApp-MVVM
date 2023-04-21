package com.newsapp.android.ui.languages

import androidx.lifecycle.viewModelScope
import com.newsapp.android.data.model.languages.Language
import com.newsapp.android.data.repository.LanguageRepository
import com.newsapp.android.ui.base.BaseViewModel
import com.newsapp.android.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageViewModel(private val languageRepository: LanguageRepository) : BaseViewModel() {

    private val _languageList = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val languageList: StateFlow<UiState<List<Language>>> = _languageList

    init {
        getLanguages()
    }

    private fun getLanguages() {

        viewModelScope.launch {

            languageRepository.getLanguages().catch {
                _languageList.value = UiState.Error(it.message.toString())
            }
                .collect {
                    _languageList.value = UiState.Success(it)
                }

        }

    }

}