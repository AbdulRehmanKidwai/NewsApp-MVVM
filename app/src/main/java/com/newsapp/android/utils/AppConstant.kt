package com.newsapp.android.utils

import com.newsapp.android.data.model.countries.Country
import com.newsapp.android.data.model.languages.Language

object AppConstant {

    const val API_KEY = "7502ab9197514f9792f796df2b3c35d7"

    const val COUNTRY_US = "us"

    const val LANGUAGE = "LANGUAGE"
    const val COUNTRY = "COUNTRY"

    val LANGUAGES = arrayListOf(

        Language("hi", "Hindi"),
        Language("en", "English"),
        Language("ar", "Arabic"),
        Language("fr", "French"),

        )

    val COUNTRIES = arrayListOf(

        Country("in", "India"),
        Country("us", "USA"),
        Country("sa", "Saudi Arabia"),
        Country("ca", "Canada"),

        )

}