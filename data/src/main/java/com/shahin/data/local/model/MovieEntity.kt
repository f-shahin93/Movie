package com.shahin.data.local.model

import androidx.room.*

@Entity(tableName = "movie_item")
data class MovieItemEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Float,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float,
    @ColumnInfo(name = "vote_count")
    val voteCount: Long,
)

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "homepage")
    val homepageUrl: String?,
    @ColumnInfo(name = "imdb_id")
    val imdbId: String?,
    @ColumnInfo(name = "runtime")
    val runtime: Long?,
    @ColumnInfo(name = "status")
    val status: String?,
    //val genres: List<Genres>,
    //val productionCompanies: List<ProductionCompany>,
    //val productionCountries: List<ProductionCountry>,
    // val spokenLanguages: List<SpokenLanguage>,
)

@Entity(tableName = "genre")
data class GenresEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "parent_id")
    val parentId: Long,
    val name: String
)

@Entity(tableName = "production_companies")
data class ProductionCompanyEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "parent_id")
    val parentId: Long,
    @ColumnInfo(name = "logo_path")
    val logoPath: String?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "origin_country")
    val originCountry: String
)

@Entity(tableName = "production_country")
data class ProductionCountryEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "iso_3166_1")
    val iso: String,
    @ColumnInfo(name = "name")
    val name: String,
)

@Entity(tableName = "spoken_language")
data class SpokenLanguageEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "english_name")
    val enName: String,
    @ColumnInfo(name = "iso_639_1")
    val iso: String,
    @ColumnInfo(name = "name")
    val name: String,
)

data class MovieWithDetails(
    @Embedded
    val movieItemEntity: MovieItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val movieDetailEntity: MovieDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val genres: List<GenresEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val productionCompanyEntity: List<ProductionCompanyEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val productionCountryEntity: List<ProductionCountryEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val spokenLanguageEntity: List<SpokenLanguageEntity>
)