package com.example.ld_02.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class HomeScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList : StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        for (m in getMovies()) {
            viewModelScope.launch(Dispatchers.IO) {
                movieRepository.addMovie(m)
            }
        }

        viewModelScope.launch{
            movieRepository.getAllMovies().collect{
                if (!it.isNullOrEmpty()) {
                    _movieList.value = it
                }
            }
        }
    }

}

