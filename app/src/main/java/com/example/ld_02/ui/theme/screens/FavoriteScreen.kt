package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.ld_02.FavoriteListSingleton
import com.example.ld_02.ui.theme.resuableComposable.ListOfMovie
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton

@Composable
fun FavoriteScreen(navController: NavController) {
    var m = remember {
        mutableStateListOf(FavoriteListSingleton.getFavorites())
    }

    Column {
        TOpAppBarWithBackButton(title = "Favorites", navController = navController)
        ListOfMovie(movieList = FavoriteListSingleton.getFavorites(), navController = navController)
    }

}