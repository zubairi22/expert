package com.bangkit.tmdb.core.domain.usecase

import com.bangkit.tmdb.core.data.Resource
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.domain.repository.IMovieAppRepository
import kotlinx.coroutines.flow.Flow

class MovieAppInteractor(private val iMovieAppRepository: IMovieAppRepository) : MovieAppUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getAllMovies()

    override fun getAllTvShows(): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getAllTvShows()

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        iMovieAppRepository.getFavoriteMovies()

    override fun getFavoriteTvShows(): Flow<List<Movie>> =
        iMovieAppRepository.getFavoriteTvShows()

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        iMovieAppRepository.setMovieFavorite(movie, state)

}