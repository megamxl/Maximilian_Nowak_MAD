package com.example.ld_02.ui.theme.resuableComposable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
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
import com.example.ld_02.models.Movie
import com.example.ld_02.navigation.Screens

@Composable
fun MovieItem(
    curr: Movie,
    boolean: Boolean,
    homescreen : Boolean,
    onItemClick: (String) -> Unit = {},
    onLiked: (Movie) -> Unit,
) {
    var isOpened by remember {
        mutableStateOf(false)
    }

    var isFav by remember {
        mutableStateOf(boolean)
    }

    var arrow by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowDown)
    }

    Card(shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(
                10.dp, 10.dp
            )
            .clickable { onItemClick("") }) {
        Column {
            Box(modifier = Modifier.height(170.dp)) {
                val painter = rememberAsyncImagePainter(model = curr.images[0])
                Image(
                    painter = painter,
                    contentDescription = "image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )

                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 15.dp, vertical = 15.dp)
                        .clickable {
                            if (homescreen) {
                                if (isFav) {
                                    isFav = !isFav
                                    onLiked(curr)
                                } else {
                                    isFav = !isFav
                                    onLiked(curr)
                                }
                            } else {
                                if (curr.isFavorite) {
                                    onLiked(curr)
                                } else {
                                    onLiked(curr)
                                }
                            }
                        },
                    tint =
                    if (isFav) {
                        Color.Cyan
                    } else {
                        Color.White
                    },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = curr.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = arrow,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (isOpened) {
                            arrow = Icons.Rounded.KeyboardArrowDown
                            isOpened = false
                        } else {
                            arrow = Icons.Rounded.KeyboardArrowUp
                            isOpened = true
                        }
                    }
                )
            }
            AnimatedVisibility(visible = isOpened) {
                Column(Modifier.padding(15.dp)) {
                    Text(text = "Director: ${curr.director}")
                    Text(text = "Release: ${curr.year}")
                    Text(text = "Genre: ${curr.genre}")
                    Text(text = "Actors: ${curr.actors}")
                    Text(text = "Rating: ${curr.rating}")
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(startIndent = 0.dp, thickness = 1.dp, color = Color.Black)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Plot :", fontWeight = FontWeight.Bold)
                    Text(text = curr.plot)
                }

            }
        }
    }
}

@Composable
fun ListOfMovie(movieList: List<Movie>, navController: NavController, onLiked: (Movie) -> Unit, homescreen: Boolean) {


    LazyColumn {
        items(movieList) { movie ->
            MovieItem(
                curr = movie, onItemClick = { movieId ->
                    navController.navigate(Screens.Detail.passId(movie.id.toString()))
                },
                onLiked = onLiked,
                boolean = movie.isFavorite,
                homescreen = homescreen
            )
        }
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