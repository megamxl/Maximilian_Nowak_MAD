package com.example.ld_02.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import kotlinx.coroutines.launch

class AddMovieScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun addMovie(movie: Movie) {
        viewModelScope.launch {
            movieRepository.addMovie(movie)
        }
    }
}

