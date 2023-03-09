package com.example.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    listOfMovie(movieList = getMovies())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun movieItem(
    curr : Movie
) {
    var isOpend by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowUp)
    }

    Card(shape = RoundedCornerShape(25.dp),modifier = Modifier.padding(0.dp,10.dp)) {
        Column() {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.comments_for_beautiful_pictures),
                    contentDescription = "image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    tint = Color.White,
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = curr.title)
                Icon(
                    imageVector = isOpend,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (isOpend == Icons.Rounded.KeyboardArrowUp) {
                            isOpend = Icons.Rounded.KeyboardArrowDown
                        } else {
                            isOpend = Icons.Rounded.KeyboardArrowUp
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun listOfMovie(movieList: List<Movie>) {
    LazyColumn (){
        items(movieList){movie -> movieItem(curr = movie)}
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ld02Theme {

    }
}