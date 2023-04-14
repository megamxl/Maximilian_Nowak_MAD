package com.example.ld_02.data

import androidx.room.*
import com.example.ld_02.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovies() : Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    suspend fun getMovieById(id :Int) :  Movie?

    @Query("SELECT * FROM Movie WHERE isFavorite = true")
    fun getFavoriteMovies() : Flow<List<Movie>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)


}