package com.shahin.data.di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UrlModule {

    companion object {
        const val WEBSITE_ENDPOINT = ""
        const val BASE_PATH_API_URL = ""
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = WEBSITE_ENDPOINT + BASE_PATH_API_URL

}