package com.example.ld_02.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.viewmodel.AddMovieScreenViewModel

class AddMovieScreenViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddMovieScreenViewModel::class.java)){
            return AddMovieScreenViewModel(movieRepository = movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}