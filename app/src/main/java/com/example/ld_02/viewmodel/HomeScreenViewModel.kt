package com.example.ld_02.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        for (m in getMovies()) {
            viewModelScope.launch(Dispatchers.IO) {
                movieRepository.addMovie(m)
            }
        }

        viewModelScope.launch {
            movieRepository.getAllMovies().collect {
                if (!it.isNullOrEmpty()) {
                    _movieList.value = it
                }
            }
        }
    }

}

