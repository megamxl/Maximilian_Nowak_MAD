package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.ld_02.ui.theme.resuableComposable.ListOfMovie
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton
import com.example.ld_02.viewmodel.MovieViewModel

@Composable
fun FavoriteScreen(navController: NavController, movieViewModel: MovieViewModel) {
    Column {
        TOpAppBarWithBackButton(title = "Favorites", navController = navController)
        ListOfMovie(movieList = movieViewModel.favList, navController = navController, onLiked = {movie, checked -> movieViewModel.changeFavoriteChecked(movie,checked)})
    }

}