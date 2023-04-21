package com.newsapp.android.di.component

import android.content.Context
import com.newsapp.android.MyApplication
import com.newsapp.android.data.local.database.DatabaseHelperImpl
import com.newsapp.android.di.ApplicationContext
import com.newsapp.android.di.api.NetworkHelper
import com.newsapp.android.di.api.NetworkService
import com.newsapp.android.di.module.ApplicationModule
import com.newsapp.android.utils.DispatcherProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDbHelper(): DatabaseHelperImpl

    fun getNetworkHelper(): NetworkHelper

    fun getDefaultDispatcher(): DispatcherProvider

}