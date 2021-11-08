package com.shahin.data.repository

import com.shahin.data.local.dao.MovieDao
import com.shahin.data.model.MovieDetail
import com.shahin.data.model.MovieShort
import com.shahin.data.model.Result
import com.shahin.data.network.services.MovieService
import com.shahin.data.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface MovieRepository {
    fun getTrendingMovieList(): Flow<Result<List<MovieShort>>>
    fun getMovie(id:Long): Flow<Result<MovieDetail>>
}

class DefaultMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepository {

    override fun getTrendingMovieList() = flow {
        emit(Result.Loading())
        movieDao.getMovieList().collect {
            emit(Result.Loading(it.map { item -> item.toDomain() }))
            emit(Result.Success(it.map { item -> item.toDomain() }))
        }

        val remoteResult = movieService.getTrendingMovieList(Constant.api_key).body()?.list
        val movieWithDetails = remoteResult?.map { it.toDetailEntity() }
        movieWithDetails?.let { movieDao.insertAll(movieWithDetails) }

        movieDao.getMovieList().collect {
            emit(Result.Success(it.map { item -> item.toDomain() }))
        }

    }.catch { ex -> emit(Result.Error(ex)) }
        .flowOn(Dispatchers.IO)

    override fun getMovie(id: Long): Flow<Result<MovieDetail>> = flow {
        emit(Result.Loading())
        movieDao.getMovieDetail(id).collect {
            emit(Result.Loading(it.toFullDomain()))
            emit(Result.Success(it.toFullDomain()))
        }

        val remoteResult = movieService.getMovie(id,Constant.api_key).body()
        remoteResult?.let { movieDao.insertAll(remoteResult.toDetailEntity()) }

        movieDao.getMovieDetail(id).collect {
            emit(Result.Success(it.toFullDomain()))
        }

    }.catch { ex -> emit(Result.Error(ex)) }
        .flowOn(Dispatchers.IO)

}