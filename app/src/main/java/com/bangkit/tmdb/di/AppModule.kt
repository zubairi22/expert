package com.bangkit.tmdb.di

import com.bangkit.tmdb.core.domain.usecase.MovieAppInteractor
import com.bangkit.tmdb.core.domain.usecase.MovieAppUseCase
import com.bangkit.tmdb.detail.DetailViewModel
import com.bangkit.tmdb.movies.MoviesViewModel
import com.bangkit.tmdb.tvshows.TvShowsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieAppUseCase> { MovieAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}