package com.bangkit.tmdb.core.data.source.local

import com.bangkit.tmdb.core.data.source.local.entity.MovieEntity
import com.bangkit.tmdb.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> {
        return mMovieDao.getMovies()
    }

    fun getAllTvShows(): Flow<List<MovieEntity>> {
        return mMovieDao.getTvShows()
    }

    fun getAllFavoriteMovies(): Flow<List<MovieEntity>> {
        return mMovieDao.getFavoriteMovies()
    }

    fun getAllFavoriteTvShows(): Flow<List<MovieEntity>> {
        return mMovieDao.getFavoriteTvShows()
    }

    suspend fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieDao.updateFavoriteMovie(movie)
    }
}