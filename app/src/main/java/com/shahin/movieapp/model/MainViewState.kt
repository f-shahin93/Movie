package com.shahin.movieapp.model

import com.shahin.data.model.MovieShort

sealed class MainViewState {
    class NavigateToDetail(val movieId: Long) : MainViewState()
    class IsLoading(val isLoading: Boolean) : MainViewState()
    class Success(val data: List<MovieShort>) : MainViewState()
    class Error(val ex: Throwable) : MainViewState()
}
