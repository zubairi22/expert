package com.bangkit.tmdb.core.domain.repository

import com.bangkit.tmdb.core.data.Resource
import com.bangkit.tmdb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieAppRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getAllTvShows(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getFavoriteTvShows(): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)

}