package com.newsapp.android.ui.countries

import androidx.lifecycle.viewModelScope
import com.newsapp.android.data.model.countries.Country
import com.newsapp.android.data.repository.CountryRepository
import com.newsapp.android.ui.base.BaseViewModel
import com.newsapp.android.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryViewModel (private val countryRepository: CountryRepository) : BaseViewModel() {

    private val _countryList = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val countryList : StateFlow<UiState<List<Country>>> = _countryList

    init {
        fetchCountries()
    }

    private fun fetchCountries() {

        viewModelScope.launch {
            countryRepository.getCountries()
                .catch {
                    _countryList.value=UiState.Error(it.message.toString())

                }
                .collect{
                    _countryList.value=UiState.Success(it)
                }
        }
    }
}