package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovieById
import com.example.ld_02.navigation.Screens
import com.example.ld_02.ui.theme.resuableComposable.ListOfMovie
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton

@Composable
fun FavoriteScreen(navController: NavController) {
    var show =listOf(getMovieById("tt2306299"), getMovieById("tt2707408"))

    Column() {
        TOpAppBarWithBackButton(title = "Favorites", navController = navController )
        ListOfMovie(movieList = show, navController = navController )
    }

}