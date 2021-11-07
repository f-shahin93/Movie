package com.shahin.movieapp.di

import com.shahin.movieapp.ui.detail.MovieDetailFragment
import com.shahin.movieapp.ui.movielist.MovieListFragment
import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {

    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }

}