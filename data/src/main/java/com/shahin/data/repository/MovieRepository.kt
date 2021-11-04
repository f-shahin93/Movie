package com.shahin.data.repository

import com.shahin.data.local.dao.MovieDao
import com.shahin.data.network.services.MovieService
import javax.inject.Inject

interface MovieRepository {

}

class DefaultMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepository{

}