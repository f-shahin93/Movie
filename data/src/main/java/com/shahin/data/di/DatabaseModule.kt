package com.shahin.data.di

import android.content.Context
import androidx.room.Room
import com.shahin.data.local.MovieDatabase
import com.shahin.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context):MovieDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_db"
        ).build()


    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.movieDao

}