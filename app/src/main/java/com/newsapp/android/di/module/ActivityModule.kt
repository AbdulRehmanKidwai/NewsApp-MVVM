package com.newsapp.android.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.newsapp.android.data.local.database.DatabaseHelperImpl
import com.newsapp.android.data.repository.CountryRepository
import com.newsapp.android.data.repository.LanguageNewsRepository
import com.newsapp.android.data.repository.LanguageRepository
import com.newsapp.android.data.repository.NewsSourceRepository
import com.newsapp.android.data.repository.SearchRepository
import com.newsapp.android.data.repository.TopHeadlinesRepository
import com.newsapp.android.di.ActivityContext
import com.newsapp.android.di.api.NetworkHelper
import com.newsapp.android.di.api.NetworkService
import com.newsapp.android.ui.base.ViewModelProviderFactory
import com.newsapp.android.ui.countries.CountryAdapter
import com.newsapp.android.ui.countries.CountryViewModel
import com.newsapp.android.ui.languages.LanguageAdapter
import com.newsapp.android.ui.languages.LanguageNewsAdapter
import com.newsapp.android.ui.languages.LanguageNewsViewModel
import com.newsapp.android.ui.languages.LanguageViewModel
import com.newsapp.android.ui.search.SearchPagingAdapter
import com.newsapp.android.ui.search.SearchViewModel
import com.newsapp.android.ui.sources.NewsSourceAdapter
import com.newsapp.android.ui.sources.NewsSourceViewModel
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
    fun provideNewsSourceViewModel(newsSourceRepository: NewsSourceRepository): NewsSourceViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(NewsSourceViewModel::class) {
            NewsSourceViewModel(newsSourceRepository)
        })[NewsSourceViewModel::class.java]
    }


    @Provides
    fun provideCountryViewModel(countryRepository: CountryRepository): CountryViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(CountryViewModel::class){
            CountryViewModel(countryRepository)
        })[CountryViewModel::class.java]
    }
    @Provides
    fun provideLanguageViewModel(languageRepository: LanguageRepository): LanguageViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(LanguageViewModel::class){
            LanguageViewModel(languageRepository)
        })[LanguageViewModel::class.java]
    }
    @Provides
    fun provideLanguageNewsViewModel(languageNewsRepository: LanguageNewsRepository): LanguageNewsViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(LanguageNewsViewModel::class){
            LanguageNewsViewModel(languageNewsRepository)
        })[LanguageNewsViewModel::class.java]
    }

    @Provides
    fun provideSearchViewModel(searchRepository: SearchRepository):SearchViewModel{
        return ViewModelProvider(activity, ViewModelProviderFactory(SearchViewModel::class){
            SearchViewModel(searchRepository)
        })[SearchViewModel::class.java]
    }


    @Provides
    fun provideTopHeadlinesAdapter() = TopHeadlinesAdapter(ArrayList())

    @Provides
    fun provideNewsSourceAdapter() = NewsSourceAdapter(ArrayList())

    @Provides
    fun provideCountryAdapter() = CountryAdapter(ArrayList())
    @Provides
    fun provideLanguageAdapter() = LanguageAdapter(ArrayList())
    @Provides
    fun provideLanguageNewsAdapter() = LanguageNewsAdapter(ArrayList())

    @Provides
    fun provideSearchPagingAdapter() = SearchPagingAdapter()

    @Provides
    fun provideTopHeadlinesRepository(networkService: NetworkService, databaseHelperImpl: DatabaseHelperImpl) = TopHeadlinesRepository(networkService, databaseHelperImpl)

    @Provides
    fun getNewsSourceRepository(networkService: NetworkService)= NewsSourceRepository(networkService)

    @Provides
    fun provideCountryRepository() = CountryRepository()

    @Provides
    fun provideLanguageRepository() = LanguageRepository()

    @Provides
    fun getLanguageNewsRepository(networkService: NetworkService)= LanguageNewsRepository(networkService)

    @Provides
    fun getSearchRepository(networkService: NetworkService)= SearchRepository(networkService)

}