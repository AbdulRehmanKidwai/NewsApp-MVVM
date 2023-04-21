package com.newsapp.android.data.repository

import com.newsapp.android.data.model.languages.Language
import com.newsapp.android.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LanguageRepository {

    fun getLanguages(): Flow<List<Language>> {

        return flow {
            emit(AppConstant.LANGUAGES)
        }

    }


}