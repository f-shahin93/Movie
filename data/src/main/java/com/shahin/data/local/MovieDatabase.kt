package com.shahin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.data.local.dao.MovieDao
import com.shahin.data.local.model.*

@Database(
    entities = [
        MovieItemEntity::class,
        MovieDetailEntity::class,
        SpokenLanguageEntity::class,
        GenresEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class
    ], version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}