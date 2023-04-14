package com.example.ld_02.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var movie: Movie? = null

    fun getMovie(id: Int) {
        viewModelScope.launch {
            movie = movieRepository.getById(id)
        }
    }

}

