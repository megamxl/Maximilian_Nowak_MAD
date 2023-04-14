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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
class AddMovieScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun addMovie(movie: Movie) {
        viewModelScope.launch{
            movieRepository.addMovie(movie)
        }
    }
}

