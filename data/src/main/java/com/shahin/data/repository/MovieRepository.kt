package com.shahin.data.repository

import com.shahin.data.local.dao.MovieDao
import com.shahin.data.model.MovieDetail
import com.shahin.data.model.MovieShort
import com.shahin.data.model.DataResult
import com.shahin.data.network.services.MovieService
import com.shahin.data.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface MovieRepository {
    fun getTrendingMovieList(): Flow<DataResult<List<MovieShort>>>
    fun getMovie(id: Long): Flow<DataResult<MovieDetail>>
    fun updateData(): Boolean
}

class DefaultMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepository {

    override fun getTrendingMovieList() = flow {
        emit(DataResult.Loading())
        movieDao.getMovieList().collect {
            emit(DataResult.Loading(it.map { item -> item.toDomain() }))
            emit(DataResult.Success(it.map { item -> item.toDomain() }))
        }

        val remoteResult = movieService.getTrendingMovieList(Constant.api_key).body()?.list
        val movieWithDetails = remoteResult?.map { it.toDetailEntity() }
        movieWithDetails?.let { movieDao.insertAll(movieWithDetails) }

        movieDao.getMovieList().collect {
            emit(DataResult.Success(it.map { item -> item.toDomain() }))
        }

    }.catch { ex -> emit(DataResult.Error(ex)) }
        .flowOn(Dispatchers.IO)

    override fun getMovie(id: Long): Flow<DataResult<MovieDetail>> = flow {
        emit(DataResult.Loading())
        movieDao.getMovieDetail(id).collect {
            emit(DataResult.Loading(it.toFullDomain()))
            emit(DataResult.Success(it.toFullDomain()))
        }

        val remoteResult = movieService.getMovie(id, Constant.api_key).body()
        remoteResult?.let { movieDao.insertAll(remoteResult.toDetailEntity()) }

        movieDao.getMovieDetail(id).collect {
            emit(DataResult.Success(it.toFullDomain()))
        }

    }.catch { ex -> emit(DataResult.Error(ex)) }
        .flowOn(Dispatchers.IO)


    override fun updateData(): Boolean =
        try {
            val remoteResult = movieService.getTrendingMovies(Constant.api_key).execute().body()?.list
            val movieWithDetails = remoteResult?.map { it.toDetailEntity() }
            movieWithDetails?.let { movieDao.insertAll(movieWithDetails) }
            remoteResult.isNullOrEmpty().not()
        } catch (ex: Exception) {
            false
        }


}