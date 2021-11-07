package com.shahin.movieapp.ui.movielist

import androidx.lifecycle.*
import com.shahin.data.repository.MovieRepository
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

}