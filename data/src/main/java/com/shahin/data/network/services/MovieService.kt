package com.shahin.data.network.services

import com.shahin.data.network.model.MovieDto
import com.shahin.data.network.model.RemoteResultsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("trending/movie/day")
    suspend fun getTrendingMovieList(
        @Query("api_key") apiKey: String
    ): Response<RemoteResultsModel<MovieDto>>

    @GET("trending/movie/day")
    fun getTrendingMovies(
        @Query("api_key") apiKey: String
    ): Call<RemoteResultsModel<MovieDto>>


    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
    ): Response<MovieDto>

}