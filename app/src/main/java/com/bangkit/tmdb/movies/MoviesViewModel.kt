package com.bangkit.tmdb.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.tmdb.core.data.Resource
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.domain.usecase.MovieAppUseCase

class MoviesViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getAllMovies().asLiveData()
    }
}