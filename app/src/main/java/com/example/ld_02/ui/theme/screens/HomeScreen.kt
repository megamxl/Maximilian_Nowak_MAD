package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ld_02.models.getMovies
import com.example.ld_02.ui.theme.Ld02Theme
import com.example.ld_02.ui.theme.resuableComposable.ListOfMovie
import com.example.ld_02.ui.theme.resuableComposable.TopBarWithFavorites
import com.example.ld_02.viewmodel.MovieViewModel


@Composable
fun HomeScreenWithAppBar(navController: NavController, viewModel: MovieViewModel) {
    Column() {
        TopBarWithFavorites(navController = navController)
        ListOfMovie(movieList =viewModel.movieList, navController = navController, onLiked = {movie, checked -> viewModel.changeFavoriteChecked(movie,checked)})
    }
}

