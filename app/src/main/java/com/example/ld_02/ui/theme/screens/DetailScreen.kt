package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ld_02.models.Movie
import com.example.ld_02.ui.theme.resuableComposable.MovieItem
import com.example.ld_02.ui.theme.resuableComposable.MovieSlider
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton
import com.example.ld_02.viewmodel.DetailScreenViewModel


@Composable
fun DetailScreen(
    Id: String,
    navController: NavController,
    detailScreenViewModel: DetailScreenViewModel,
    onLiked: (Movie) -> Unit
) {

    detailScreenViewModel.getMovie(Id.toInt())
    val curr = detailScreenViewModel.movie

    Column {
        if (curr != null) {
            TOpAppBarWithBackButton(curr.title, navController)
            MovieItem(curr = curr, onLiked = onLiked, boolean = curr.isFavorite, homescreen = true)
            Divider(startIndent = 0.dp, thickness = 1.dp, color = Color.Black)
            MovieSlider(links = curr.images)
        }
    }

}

