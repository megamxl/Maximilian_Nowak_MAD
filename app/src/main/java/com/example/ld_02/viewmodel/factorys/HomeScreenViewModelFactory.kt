package com.example.ld_02.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.viewmodel.HomeScreenViewModel

class HomeScreenViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(movieRepository = movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}