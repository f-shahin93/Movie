package com.shahin.data.di

import com.shahin.data.repository.DefaultMovieRepository
import com.shahin.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repository: DefaultMovieRepository): MovieRepository

}