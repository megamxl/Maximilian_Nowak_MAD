package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ld_02.models.getMovieById
import com.example.ld_02.ui.theme.resuableComposable.MovieItem
import com.example.ld_02.ui.theme.resuableComposable.MovieSlider
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton


@Composable
fun DetailScreen(Id: String, navController: NavController) {

    val curr = getMovieById(Id)

    Column {
        TOpAppBarWithBackButton(curr.title, navController)
        MovieItem(curr = curr)
        Divider(startIndent = 0.dp, thickness = 1.dp, color = Color.Black)
        MovieSlider(links = curr.images)
    }

}

