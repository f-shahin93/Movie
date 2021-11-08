package com.shahin.movieapp.ui.detail.intent


sealed class MovieDetailViewIntent(){
    class GetDetail(val id: Long) : MovieDetailViewIntent()
}
