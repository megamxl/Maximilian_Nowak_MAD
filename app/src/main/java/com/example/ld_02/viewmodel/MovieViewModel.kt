package com.example.ld_02.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovies

class MovieViewModel : ViewModel() {


    private val _movieList = getMovies().toMutableStateList()
    val movieList : List<Movie> get() = _movieList

    val favList : List<Movie> get() = _movieList.filter { it.isFavorite.value }

    fun changeFavoriteChecked(movie: Movie, checked : Boolean) {
        _movieList.find { it.id == movie.id }?.isFavorite?.value = checked
    }

    fun addMovie(movie: Movie) {
        _movieList.add(movie)
    }



}