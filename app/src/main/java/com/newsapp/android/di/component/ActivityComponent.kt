package com.newsapp.android.di.component

import com.newsapp.android.di.ActivityScope
import com.newsapp.android.di.module.ActivityModule
import com.newsapp.android.ui.topHeadlines.TopHeadlinesActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity:TopHeadlinesActivity)

}