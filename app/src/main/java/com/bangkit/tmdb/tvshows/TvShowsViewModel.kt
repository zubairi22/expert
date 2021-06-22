package com.bangkit.tmdb.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.tmdb.core.data.Resource
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.domain.usecase.MovieAppUseCase

class TvShowsViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {
    fun getTvShows(): LiveData<Resource<List<Movie>>> =
        movieAppUseCase.getAllTvShows().asLiveData()
}