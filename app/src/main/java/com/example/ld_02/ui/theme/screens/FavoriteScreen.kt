package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.ld_02.models.Movie
import com.example.ld_02.ui.theme.resuableComposable.ListOfMovie
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton
import com.example.ld_02.viewmodel.FavoriteScreenViewModel
import com.example.ld_02.viewmodel.HomeScreenViewModel

@Composable
fun FavoriteScreen(navController: NavController, viewModel : FavoriteScreenViewModel,  onLiked: (Movie) -> Unit) {

    val favlist =  viewModel.favList.collectAsState()

    Column {
        TOpAppBarWithBackButton(title = "Favorites", navController = navController)
        ListOfMovie(movieList = favlist.value, navController = navController, onLiked = onLiked)
    }



}