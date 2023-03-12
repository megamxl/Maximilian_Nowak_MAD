package com.example.ld_02.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ld_02.ui.theme.screens.DetailScreen
import com.example.ld_02.ui.theme.screens.FavoriteScreen
import com.example.ld_02.ui.theme.screens.HomeScreenWithAppBar

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ){
        composable(
            route = Screens.Home.route
        ) {
            HomeScreenWithAppBar(navController)
        }
        composable(
            route = Screens.Detail.route,
            arguments = listOf(navArgument(MOVIE_KEY){
                type = NavType.StringType
            })
        ) {
            DetailScreen(
                Id =it.arguments?.getString(MOVIE_KEY).toString(),
                navController = navController)
        }
        composable(
            route = Screens.Favorites.route
        ){
            FavoriteScreen(navController = navController)
        }

    }

}