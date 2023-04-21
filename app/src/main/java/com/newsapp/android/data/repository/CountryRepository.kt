package com.newsapp.android.data.repository

import com.newsapp.android.data.model.countries.Country
import com.newsapp.android.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

class CountryRepository {

    fun getCountries():Flow<List<Country>>{

        return flow {
            emit(AppConstant.COUNTRIES)
        }

    }

}