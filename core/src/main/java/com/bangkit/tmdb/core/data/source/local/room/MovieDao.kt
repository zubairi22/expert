package com.bangkit.tmdb.core.data.source.local.room

import androidx.room.*
import com.bangkit.tmdb.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities WHERE isTvShow = 0")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities WHERE isTvShow = 1")
    fun getTvShows(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where favorite = 1 and isTvShow = 0")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where favorite = 1 and isTvShow = 1")
    fun getFavoriteTvShows(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}