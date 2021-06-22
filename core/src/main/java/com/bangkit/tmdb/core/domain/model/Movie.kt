package com.bangkit.tmdb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    var overview: String,
    var originalLanguage: String,
    var releaseDate: String,
    var voteAverage: Double,
    var id: Int,
    var title: String,
    var posterPath: String,
    var backdropPath: String?,
    var favorite: Boolean = false,
    var isTvShows: Boolean = false
) : Parcelable