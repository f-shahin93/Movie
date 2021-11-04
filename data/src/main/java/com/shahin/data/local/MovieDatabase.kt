package com.shahin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.data.local.dao.MovieDao
import com.shahin.data.local.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}