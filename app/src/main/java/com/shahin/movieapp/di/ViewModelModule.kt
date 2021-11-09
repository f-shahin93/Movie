package com.shahin.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shahin.movieapp.ui.detail.MovieDetailViewModel
import com.shahin.movieapp.ui.movielist.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /*@Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel*/

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

}