package com.shahin.movieapp.intent

sealed class MainViewIntent {
    class IsNavigateToDetail(val movieId: Long) : MainViewIntent()
    object GetList : MainViewIntent()
}