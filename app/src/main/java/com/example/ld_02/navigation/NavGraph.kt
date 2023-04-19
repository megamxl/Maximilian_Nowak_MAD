package com.example.ld_02.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ld_02.data.MovieDatabase
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.Movie
import com.example.ld_02.ui.theme.screens.AddMovieScreen
import com.example.ld_02.ui.theme.screens.DetailScreen
import com.example.ld_02.ui.theme.screens.FavoriteScreen
import com.example.ld_02.ui.theme.screens.HomeScreenWithAppBar
import com.example.ld_02.viewmodel.AddMovieScreenViewModel
import com.example.ld_02.viewmodel.DetailScreenViewModel
import com.example.ld_02.viewmodel.FavoriteScreenViewModel
import com.example.ld_02.viewmodel.HomeScreenViewModel
import com.example.ld_02.viewmodel.factorys.AddMovieScreenViewModelFactory
import com.example.ld_02.viewmodel.factorys.DetailScreenViewModelFactory
import com.example.ld_02.viewmodel.factorys.FavoriteScreenViewModelFactory
import com.example.ld_02.viewmodel.factorys.HomeScreenViewModelFactory

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())

    val homeScreenFactory = HomeScreenViewModelFactory(movieRepository = repository)
    val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = homeScreenFactory)

    val addMovieScreenViewModelFactory =
        AddMovieScreenViewModelFactory(movieRepository = repository)
    val addMovieScreenViewModel: AddMovieScreenViewModel =
        viewModel(factory = addMovieScreenViewModelFactory)

    val detailScreenViewModelFactory = DetailScreenViewModelFactory(movieRepository = repository)
    val detailScreenViewModel: DetailScreenViewModel =
        viewModel(factory = detailScreenViewModelFactory)

    val favoriteScreenViewModelFactory =
        FavoriteScreenViewModelFactory(movieRepository = repository)
    val favoriteScreenViewModel: FavoriteScreenViewModel =
        viewModel(factory = favoriteScreenViewModelFactory)

    val onLiked = { movie: Movie -> favoriteScreenViewModel.changeFavoriteChecked(movie) }

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(
            route = Screens.Home.route
        ) {
            HomeScreenWithAppBar(navController, homeScreenViewModel, onLiked)
        }
        composable(
            route = Screens.Detail.route,
            arguments = listOf(navArgument(MOVIE_KEY) {
                type = NavType.StringType
            })
        ) {
            DetailScreen(
                Id = it.arguments?.getString(MOVIE_KEY).toString(),
                navController = navController,
                detailScreenViewModel = detailScreenViewModel,
                onLiked = onLiked
            )
        }
        composable(
            route = Screens.Favorites.route
        ) {
            FavoriteScreen(navController = navController, favoriteScreenViewModel, onLiked)
        }
        composable(
            route = Screens.AddMovie.route
        ) {
            AddMovieScreen(navController = navController, addMovieScreenViewModel)
        }

    }

}