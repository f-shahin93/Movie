package com.shahin.movieapp.intent

import android.content.Context

sealed class MainViewIntent {
    class IsNavigateToDetail(val movieId: Long) : MainViewIntent()
    object GetList : MainViewIntent()
    class SetupWorkManager(val context: Context):MainViewIntent()
}