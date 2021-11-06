package com.shahin.data.repository

import com.shahin.data.local.model.*
import com.shahin.data.model.*
import com.shahin.data.network.model.*

fun MovieDto.toItemEntity(): MovieItemEntity =
    MovieItemEntity(
        id = id,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

fun MovieDto.toDetailEntity(): MovieWithDetails =
    MovieWithDetails(
        MovieItemEntity(
            id = id,
            backdropPath = backdropPath,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        ),
        MovieDetailEntity(
            id = id,
            homepageUrl = homepageUrl,
            imdbId = imdbId,
            runtime = runtime,
            status = status
        ),
        genres?.map { it.toEntity(id) } ?: emptyList(),
        productionCompanies?.map { it.toEntity(id) } ?: emptyList(),
        productionCountries?.map { it.toEntity(id) } ?: emptyList(),
        spokenLanguages?.map { it.toEntity(id) } ?: emptyList()
    )

fun GenresDto.toEntity(parentId: Long): GenresEntity =
    GenresEntity(id, parentId, name)

fun ProductionCompanyDto.toEntity(parentId: Long): ProductionCompanyEntity =
    ProductionCompanyEntity(id, parentId, logoPath, name, originCountry)

fun ProductionCountryDto.toEntity(id: Long): ProductionCountryEntity =
    ProductionCountryEntity(id, iso, name)

fun SpokenLanguageDto.toEntity(id: Long): SpokenLanguageEntity =
    SpokenLanguageEntity(id, enName, iso, name)

fun MovieWithDetails.toDomain(): MovieShort =
    movieItemEntity.run {
        MovieShort(
            id,
            backdropPath,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            title,
            video,
            voteAverage,
            voteCount
        )
    }

fun MovieWithDetails.toFullDomain(): MovieDetail {
    movieItemEntity.run {
        movieDetailEntity.run {
            return MovieDetail(
                id = id,
                backdropPath = backdropPath,
                genres = genres.map { it.toDomain() },
                homepageUrl = homepageUrl,
                imdbId = imdbId,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                productionCompanies = productionCompanyEntity.map { it.toDomain() },
                productionCountries = productionCountryEntity.map { it.toDomain() },
                releaseDate = releaseDate,
                runtime =runtime,
                spokenLanguages = spokenLanguageEntity.map { it.toDomain() },
                status = status,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount,
            )
        }
    }
}

fun GenresEntity.toDomain(): Genres = Genres(id, name)

fun ProductionCompanyEntity.toDomain(): ProductionCompany =
    ProductionCompany(id, logoPath, name, originCountry)

fun ProductionCountryEntity.toDomain(): ProductionCountry = ProductionCountry(iso, name)

fun SpokenLanguageEntity.toDomain(): SpokenLanguage = SpokenLanguage(enName, iso, name)