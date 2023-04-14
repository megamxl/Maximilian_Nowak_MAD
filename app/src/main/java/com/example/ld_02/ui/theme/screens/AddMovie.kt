package com.example.ld_02.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ld_02.AddMovieFormEvent
import com.example.ld_02.R
import com.example.ld_02.models.Genre
import com.example.ld_02.models.ListItemSelectable
import com.example.ld_02.models.Movie
import com.example.ld_02.navigation.Screens
import com.example.ld_02.ui.*
import com.example.ld_02.ui.theme.resuableComposable.TOpAppBarWithBackButton
import com.example.ld_02.viewmodel.AddMovieScreenViewModel
import com.example.ld_02.viewmodel.HomeScreenViewModel
import com.example.ld_02.viewmodel.ValidationScreenViewmodel
import kotlin.random.Random

@Composable
fun AddMovieScreen(navController: NavController, viewModel: AddMovieScreenViewModel) {
    val scaffoldState = rememberScaffoldState()

    val addMovie = { m: Movie -> viewModel.addMovie(m) }
    val navHome = {navController.navigate(Screens.Home.route)}

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TOpAppBarWithBackButton(title = "Add A Movie", navController = navController)
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), addMovie, navHome)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, onItemClick: (Movie) -> Unit, navHome: () -> Unit) {


    val viewModel: ValidationScreenViewmodel = viewModel()

    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = context) {
        viewModel.validationEvent.collect {
            when (it) {
                ValidationScreenViewmodel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context, "Added Movie", Toast.LENGTH_LONG
                    ).show()
                    navHome()
                }
            }
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(genres.map { genre ->
                    ListItemSelectable(
                        title = genre.toString(), isSelected = false
                    )
                })
            }

            OutlinedTextField(
                value = state.tile,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.onEvent(AddMovieFormEvent.TitleInputChanged(it))
                },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = state.tileError != null
            )
            ErrorText(erorrMsg = state.tileError)


            OutlinedTextField(
                value = state.year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onEvent(AddMovieFormEvent.YearInputChanged(it)) },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = state.yearError != null
            )
            ErrorText(erorrMsg = state.yearError)

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp), rows = GridCells.Fixed(3)
            ) {
                items(genreItems) { genreItem ->
                    Chip(modifier = Modifier.padding(2.dp), colors = ChipDefaults.chipColors(
                        backgroundColor = if (genreItem.isSelected) colorResource(id = R.color.purple_200)
                        else colorResource(id = R.color.white)
                    ), onClick = {
                        genreItems = genreItems.map {
                            if (it.title == genreItem.title) {
                                genreItem.copy(isSelected = !genreItem.isSelected)
                            } else {
                                it
                            }
                        }
                        viewModel.onEvent(AddMovieFormEvent.GenreInputChanged(genreItems))
                    }) {
                        Text(text = genreItem.title)
                    }
                }
            }

            ErrorText(erorrMsg = state.genresError)

            OutlinedTextField(
                value = state.director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onEvent(AddMovieFormEvent.DirectorInputChanged(it)) },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = state.directorError != null
            )
            ErrorText(erorrMsg = state.directorError)


            OutlinedTextField(
                value = state.actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewModel.onEvent(AddMovieFormEvent.ActorsInputChanged(it)) },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = state.actorsError != null
            )
            ErrorText(erorrMsg = state.actorsError)

            OutlinedTextField(
                value = state.plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { viewModel.onEvent(AddMovieFormEvent.PlotInputChanged(it)) },
                label = {
                    Text(
                        textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)
                    )
                },
                isError = state.plotError != null
            )
            ErrorText(erorrMsg = state.plotError)

            OutlinedTextField(
                value = state.rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    if (it.startsWith("0")) {
                        viewModel.onEvent(AddMovieFormEvent.RatingInputChanged("0"))
                    } else {
                        viewModel.onEvent(AddMovieFormEvent.RatingInputChanged(it))
                    }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = state.ratingError != null,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            ErrorText(erorrMsg = state.ratingError)
            Button(enabled = state.isButtonEnabled, onClick = {
                viewModel.onEvent(AddMovieFormEvent.Submit)
                onItemClick(Movie(
                    title = state.tile,
                    year = state.year,
                    genre = listOf(Genre.ACTION),
                    director = state.director,
                    actors = state.actors,
                    plot = state.plot,
                    images= listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSn5eWF8-t1z9Tp_1drxzqKMPGeQAwwSr7lMA&usqp=CAU","https://img.freepik.com/free-photo/colorful-beautiful-flowers-background-blossom-floral-bouquet-decoration-garden-flowers-plant-pattern-wallpapers-greeting-cards-postcards-design-wedding-invites_90220-1105.jpg"),
                    rating = state.rating.toFloat()
                )
                )
            }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}
@Composable
fun ErrorText(erorrMsg: String?) {
    if (erorrMsg != null) {
        Text(
            text = erorrMsg,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}