package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.ld_02.models.Movie
import com.example.ld_02.ui.theme.resuableComposable.ListOfMovie
import com.example.ld_02.ui.theme.resuableComposable.TopBarWithFavorites
import com.example.ld_02.viewmodel.HomeScreenViewModel


@Composable
fun HomeScreenWithAppBar(
    navController: NavController,
    viewModel: HomeScreenViewModel,
    onLiked: (Movie) -> Unit
) {
    Column {
        val movieList = viewModel.movieList.collectAsState()

        TopBarWithFavorites(navController = navController)
        ListOfMovie(movieList = movieList.value, navController = navController, onLiked = onLiked, homescreen = true)
    }
}

