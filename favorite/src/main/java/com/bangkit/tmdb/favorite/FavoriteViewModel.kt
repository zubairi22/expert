package com.bangkit.tmdb.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.domain.usecase.MovieAppUseCase

class FavoriteViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {

    fun getFavoriteMovies(): LiveData<List<Movie>> =
        movieAppUseCase.getFavoriteMovies().asLiveData()

    fun getFavoriteTvShows(): LiveData<List<Movie>> =
        movieAppUseCase.getFavoriteTvShows().asLiveData()

    fun setFavorite(Movie: Movie, newState: Boolean) {
        movieAppUseCase.setMovieFavorite(Movie, newState)
    }
}

