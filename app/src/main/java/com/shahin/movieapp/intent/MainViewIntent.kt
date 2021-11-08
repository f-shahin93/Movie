package com.shahin.movieapp.intent

sealed class MainViewIntent {
    class IsNavigateToDetail(movieId: Long) : MainViewIntent()
    object GetList : MainViewIntent()
}