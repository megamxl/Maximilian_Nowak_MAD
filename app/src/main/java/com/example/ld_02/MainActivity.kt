package com.example.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovies
import com.example.ld_02.ui.theme.Ld02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ld02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Column {
                        TopBar()

                        ListOfMovie(movieList = getMovies())
                    }
                }
            }
        }
    }
}


@Composable
fun MovieItem(
    curr: Movie
) {
    var isOpened by remember {
        mutableStateOf(false)
    }

    var isLiked by remember {
        mutableStateOf(Color.White)
    }
    var arrow by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowDown)
    }

    Card(shape = RoundedCornerShape(25.dp), modifier = Modifier.padding(0.dp, 10.dp)) {
        Column {
            Box(modifier = Modifier.height(170.dp)) {
                val painter = rememberAsyncImagePainter(model = curr.images.random())
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
                            isLiked = if (isLiked == Color.White) {
                                Color.Cyan
                            } else {
                                Color.White
                            }
                        },
                    tint = isLiked,
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
                    Text(text = "Rating: ${curr.rating}" )
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
fun ListOfMovie(movieList: List<Movie>) {
    LazyColumn {
        items(movieList) { movie -> MovieItem(curr = movie) }
    }
}

@Composable
fun TopBar() {
    var isFavoritesCollapsed by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        elevation = 4.dp,
        title = {
            Text("My Movie Palace")
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        actions = {
            IconButton(
                onClick = {
                    isFavoritesCollapsed = true
                },
            ) {
                Icon(Icons.Filled.MoreVert, null)
            }
            DropdownMenu(
                expanded = isFavoritesCollapsed,
                onDismissRequest = {
                    isFavoritesCollapsed = false
                }
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Row {
                        Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
                        Text(text = " Favorites")
                    }
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ld02Theme {

    }
}