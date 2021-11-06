package com.shahin.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("backdrop_path")
    val backdropPath: String, // "backdrop_path": "/oE6bhqqVFyIECtBzqIuvh6JdaB5.jpg"
    @SerializedName("genres")
    val genres: List<GenresDto>?,
    @SerializedName("homepage")
    val homepageUrl: String?, // "homepage": "https://tv.apple.com/movie/umc.cmc.47dkj9f2ho3h8dwxixflz65q5"
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String, // "poster_path": "/jKuDyqx7jrjiR9cDzB5pxzhJAdv.jpg"
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDto>?,
    @SerializedName("release_date")
    val releaseDate: String, //"release_date": "2021-11-04"
    @SerializedName("runtime")
    val runtime: Long?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Long,
)

data class GenresDto(
    val id: Long,
    val name: String
)

data class ProductionCompanyDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("logo_path")
    val logoPath: String?,// "logo_path": "/cEaxANEisCqeEoRvODv2dO1I0iI.png",
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)

data class ProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso: String,
    @SerializedName("name")
    val name: String,
)

data class SpokenLanguageDto(
    @SerializedName("english_name")
    val enName: String,
    @SerializedName("iso_639_1")
    val iso: String,
    @SerializedName("name")
    val name: String,
)

data class RemoteResultsModel<T>(
    @SerializedName("results")
    val list: List<T> = emptyList(),
    @SerializedName("page")
    val page: Long = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)