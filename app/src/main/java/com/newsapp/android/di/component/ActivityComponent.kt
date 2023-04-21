package com.newsapp.android.di.component

import com.newsapp.android.ui.search.SearchActivity
import com.newsapp.android.di.ActivityScope
import com.newsapp.android.di.module.ActivityModule
import com.newsapp.android.ui.countries.CountryActivity
import com.newsapp.android.ui.languages.LanguageActivity
import com.newsapp.android.ui.languages.LanguageNewsActivity
import com.newsapp.android.ui.sources.NewsSourceActivity
import com.newsapp.android.ui.topHeadlines.TopHeadlinesActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity:TopHeadlinesActivity)
    fun inject(activity:NewsSourceActivity)
    fun inject(activity:CountryActivity)
    fun inject(activity:LanguageActivity)
    fun inject(activity:LanguageNewsActivity)
    fun inject(activity: SearchActivity)

}