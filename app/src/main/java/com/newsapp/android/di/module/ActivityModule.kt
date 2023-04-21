package com.newsapp.android.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.newsapp.android.data.local.database.DatabaseHelperImpl
import com.newsapp.android.data.repository.TopHeadlinesRepository
import com.newsapp.android.di.ActivityContext
import com.newsapp.android.di.api.NetworkHelper
import com.newsapp.android.di.api.NetworkService
import com.newsapp.android.ui.base.ViewModelProviderFactory
import com.newsapp.android.ui.topHeadlines.TopHeadlinesAdapter
import com.newsapp.android.ui.topHeadlines.TopHeadlinesViewModel
import com.newsapp.android.utils.DispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }


    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlinesRepository, networkHelper: NetworkHelper,
                                 dispatcherProvider: DispatcherProvider
    ):
            TopHeadlinesViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(TopHeadlinesViewModel::class) {
            TopHeadlinesViewModel(topHeadlineRepository, networkHelper, dispatcherProvider)
        })[TopHeadlinesViewModel::class.java]
    }


    @Provides
    fun provideTopHeadlinesAdapter() = TopHeadlinesAdapter(ArrayList())



    @Provides
    fun provideTopHeadlinesRepository(networkService: NetworkService, databaseHelperImpl: DatabaseHelperImpl) = TopHeadlinesRepository(networkService, databaseHelperImpl)

}