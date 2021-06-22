package com.bangkit.tmdb.core.utils

import com.bangkit.tmdb.core.data.source.local.entity.MovieEntity
import com.bangkit.tmdb.core.data.source.remote.response.MovieResponse
import com.bangkit.tmdb.core.data.source.remote.response.TvShowResponse
import com.bangkit.tmdb.core.domain.model.Movie

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.overview,
                it.originalLanguage,
                it.releaseDate,
                it.voteAverage,
                it.id,
                it.title,
                it.posterPath,
                it.backdropPath,
                favorite = false,
                isTvShows = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.overview,
                it.originalLanguage,
                it.firstAirDate,
                it.voteAverage,
                it.id,
                it.name,
                it.posterPath,
                it.backdropPath,
                favorite = false,
                isTvShows = true
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                it.overview,
                it.originalLanguage,
                it.releaseDate,
                it.voteAverage,
                it.id,
                it.title,
                it.posterPath,
                it.backdropPath,
                favorite = it.favorite,
                isTvShows = it.isTvShows
            )
        }
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            input.overview,
            input.originalLanguage,
            input.releaseDate,
            input.voteAverage,
            input.id,
            input.title,
            input.posterPath,
            input.backdropPath,
            favorite = input.favorite,
            isTvShows = input.isTvShows
        )
    }
}