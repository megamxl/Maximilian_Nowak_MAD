package com.example.ld_02.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    private val _favList = MutableStateFlow(listOf<Movie>())
    val favList: StateFlow<List<Movie>> = _favList.asStateFlow()

    init {
        getFavs()
    }

    fun getFavs() {
        viewModelScope.launch {
            movieRepository.getFavoriteMoves()?.collect {
                if (it != null) {
                    _favList.value = it
                }
            }
        }
    }

    fun changeFavoriteChecked(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            movieRepository.updateMovie(movie)
        }
    }
}

