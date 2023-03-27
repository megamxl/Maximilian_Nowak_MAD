package com.example.ld_02.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovies

class MovieViewModel : ViewModel() {


    private val _movieList = getMovies().toMutableStateList()
    val movieList : List<Movie> get() = _movieList

    private val _favList =  emptyList<Movie>().toMutableStateList()
    val favList : List<Movie> get() = _favList

    fun changeFavoriteChecked(movie: Movie, checked : Boolean) {
        if (checked) {
            _favList.find { it.id == movie.id }?.isFavorite = true
            _movieList.find { it.id == movie.id }?.isFavorite = true
            if (_favList.find { it.id == movie.id }?.id == null) {
                _favList.add(movie)
            }
        } else {
            if (_favList.find { it.id == movie.id }?.id != null) {
                _favList.find { it.id == movie.id }?.isFavorite = false
                _movieList.find { it.id == movie.id }?.isFavorite = false
                _favList.remove(movie)
            }
        }
    }


}