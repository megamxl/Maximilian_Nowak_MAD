package com.example.ld_02.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.viewmodel.DetailScreenViewModel

class DetailScreenViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailScreenViewModel::class.java)) {
            return DetailScreenViewModel(movieRepository = movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}