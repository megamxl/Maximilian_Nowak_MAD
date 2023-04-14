package com.example.ld_02.data

import com.example.ld_02.models.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val  movieDao: MovieDao) {

    suspend fun addMovie(movie: Movie) = movieDao.insertMovie(movie = movie)
    suspend fun updateMovie(movie: Movie) = movieDao.updateMovie(movie = movie)
    suspend fun deleteMove(movie: Movie) = movieDao.deleteMovie(movie = movie)
    fun getAllMovies() : Flow<List<Movie>> = movieDao.getMovies()
    fun getFavoriteMoves() : Flow<List<Movie>>? = movieDao.getFavoriteMovies()
    suspend fun getById(id: Int) : Movie? = movieDao.getMovieById(id)

}