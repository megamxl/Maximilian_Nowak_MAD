package com.example.ld_02.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.viewmodel.FavoriteScreenViewModel
import com.example.ld_02.viewmodel.HomeScreenViewModel

class FavoriteScreenViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoriteScreenViewModel::class.java)){
            return FavoriteScreenViewModel(movieRepository = movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}