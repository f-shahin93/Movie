package com.shahin.movieapp.di

import android.app.Application
import android.content.Context
import com.shahin.data.local.AppPrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppPrefManager(context: Context): AppPrefManager =
        AppPrefManager(context)

}