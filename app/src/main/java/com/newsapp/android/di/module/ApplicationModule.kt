package com.newsapp.android.di.module

import android.content.Context
import com.newsapp.android.di.api.ApplicationInterceptor
import com.newsapp.android.di.api.NetworkHelper
import com.newsapp.android.di.api.NetworkService
import com.newsapp.android.MyApplication
import com.newsapp.android.di.ApplicationContext
import com.newsapp.android.di.BaseUrl
import com.newsapp.android.di.DatabaseName
import com.newsapp.android.utils.DefaultDispatcher
import com.newsapp.android.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://newsapi.org/v2/"


    @DatabaseName
    @Provides
    fun provideDatabaseName(): String="news_app_offline"

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: ApplicationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(@BaseUrl baseUrl: String, gsonConverterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient):

            NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)//Ye inject nahi kr rhe hain
            .build()
            .create(NetworkService::class.java)
    }


    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context)= NetworkHelper(context)


    @Singleton
    @Provides
    fun provideDefaultDispatcher(): DispatcherProvider = DefaultDispatcher()

}