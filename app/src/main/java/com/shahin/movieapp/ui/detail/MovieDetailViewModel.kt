package com.shahin.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import com.shahin.data.repository.MovieRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

}