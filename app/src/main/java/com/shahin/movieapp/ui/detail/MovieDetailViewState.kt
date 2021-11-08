package com.shahin.movieapp.ui.detail

import com.shahin.data.model.MovieDetail


sealed class MovieDetailViewState {
    class IsLoading(val isLoading: Boolean) : MovieDetailViewState()
    class Success(val data: MovieDetail) : MovieDetailViewState()
    class Error(val ex: Throwable) : MovieDetailViewState()
}
