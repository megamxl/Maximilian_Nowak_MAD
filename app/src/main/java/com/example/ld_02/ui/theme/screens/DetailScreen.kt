package com.example.ld_02.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ld_02.models.getMovieById
import com.example.ld_02.ui.theme.resuableComposable.MovieItem
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

@Composable
fun MovieSlider(links: List<String>) {
    Column {
        Text(
            text = "Movie images",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            modifier = Modifier.fillMaxWidth()
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(items = links, itemContent = {
                val painter = rememberAsyncImagePainter(model = it)
                Image(
                    painter = painter,
                    contentDescription = "image",
                    modifier = Modifier
                        .width(250.dp)
                        .height(400.dp)
                        .padding(10.dp),
                    contentScale = ContentScale.FillHeight
                )
            })
        }
    }
}
