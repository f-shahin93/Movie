package com.shahin.data.model


data class MovieShort(
    val id: Long,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Long,
)

data class MovieDetail(
    val id: Long,
    val backdropPath: String,
    val genres: List<Genres>,
    val homepageUrl: String?,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val runtime: Long?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Long,
)

data class Genres(
    val id: Long,
    val name: String
)

data class ProductionCompany(
    val id: Long,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class ProductionCountry(
    val iso: String,
    val name: String,
)

data class SpokenLanguage(
    val enName: String,
    val iso: String,
    val name: String,
)
