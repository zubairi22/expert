package com.bangkit.tmdb.detail

import androidx.lifecycle.ViewModel
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.domain.usecase.MovieAppUseCase

class DetailViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) {
        movieAppUseCase.setMovieFavorite(movie, newStatus)
    }
}
