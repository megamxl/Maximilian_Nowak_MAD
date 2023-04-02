package com.example.ld_02.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ld_02.ui.theme.screens.AddMovieScreen
import com.example.ld_02.ui.theme.screens.DetailScreen
import com.example.ld_02.ui.theme.screens.FavoriteScreen
import com.example.ld_02.ui.theme.screens.HomeScreenWithAppBar
import com.example.ld_02.viewmodel.MovieViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val viewModel: MovieViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ){
        composable(
            route = Screens.Home.route
        ) {
            HomeScreenWithAppBar(navController, viewModel)
        }
        composable(
            route = Screens.Detail.route,
            arguments = listOf(navArgument(MOVIE_KEY){
                type = NavType.StringType
            })
        ) {
            DetailScreen(
                Id =it.arguments?.getString(MOVIE_KEY).toString(),
                navController = navController,
                viewModel
            )
        }
        composable(
            route = Screens.Favorites.route
        ){
            FavoriteScreen(navController = navController, viewModel)
        }
        composable(
            route = Screens.AddMovie.route
        ){
            AddMovieScreen(navController = navController, viewModel)
        }

    }

}