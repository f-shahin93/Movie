package com.shahin.data.di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UrlModule {

    companion object {
        const val WEBSITE_ENDPOINT = "https://api.themoviedb.org/"
        const val BASE_PATH = "3/"
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = WEBSITE_ENDPOINT + BASE_PATH

}