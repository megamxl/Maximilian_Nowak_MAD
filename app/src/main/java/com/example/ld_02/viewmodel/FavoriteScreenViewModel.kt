package com.example.ld_02.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovies
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class FavoriteScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    private val _favList = MutableStateFlow(listOf<Movie>())
    val favList : StateFlow<List<Movie>> = _favList.asStateFlow()

    init {
        viewModelScope.launch{
            movieRepository.getFavoriteMoves()?.collect{
                if (!it.isNullOrEmpty()) {
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

