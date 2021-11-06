package com.shahin.data.repository

import com.shahin.data.local.dao.MovieDao
import com.shahin.data.model.MovieShort
import com.shahin.data.network.services.MovieService
import com.shahin.data.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface MovieRepository {
    fun getTrendingMovieList(): Flow<List<MovieShort>>
}

class DefaultMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepository {

    override fun getTrendingMovieList() = flow<List<MovieShort>> {

        val remoteResult = movieService.getTrendingMovieList(Constant.api_key).body()?.list

        val movieWithDetails = remoteResult?.map { it.toDetailEntity() }
        movieWithDetails?.let { movieDao.insertAll(movieWithDetails) }

    }.catch { }
        .flowOn(Dispatchers.IO)

}