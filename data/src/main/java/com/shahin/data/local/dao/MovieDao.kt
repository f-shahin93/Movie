package com.shahin.data.local.dao

import androidx.room.*
import com.shahin.data.local.model.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieItem(movieItemEntity: List<MovieItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insert(movieItemEntity: MovieItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insert(movieDetailEntity: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertMovieDetail(movieDetailEntity: List<MovieDetailEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertGenres(genresEntity: List<GenresEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertProductionCountries(productionCountryEntity: List<ProductionCountryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertProductionCompanies(productionCompanyEntity: List<ProductionCompanyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertSpokenLanguages(spokenLanguageEntity: List<SpokenLanguageEntity>)

    @Transaction
    open fun insertAll(movieWithDetails: List<MovieWithDetails>) {
        insertMovieItem(movieItemEntity = movieWithDetails.map { it.movieItemEntity })
        insertMovieDetail(movieDetailEntity = movieWithDetails.map { it.movieDetailEntity })
        insertGenres(genresEntity = movieWithDetails.flatMap { it.genres })
        insertProductionCountries(productionCountryEntity = movieWithDetails.flatMap { it.productionCountryEntity })
        insertProductionCompanies(productionCompanyEntity = movieWithDetails.flatMap { it.productionCompanyEntity })
        insertSpokenLanguages(spokenLanguageEntity = movieWithDetails.flatMap { it.spokenLanguageEntity })
    }

    @Transaction
    open fun insertAll(movieWithDetail :MovieWithDetails){
        insert(movieWithDetail.movieItemEntity)
        insert(movieWithDetail.movieDetailEntity)
        insertGenres(movieWithDetail.genres)
        insertProductionCountries(movieWithDetail.productionCountryEntity)
        insertProductionCompanies(movieWithDetail.productionCompanyEntity)
        insertSpokenLanguages(movieWithDetail.spokenLanguageEntity)
    }

    @Query("SELECT * FROM movie_item")
    abstract fun getMovieList(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM movie_item WHERE id = :id")
    abstract fun getMovieDetail(id:Long): Flow<MovieWithDetails>

}