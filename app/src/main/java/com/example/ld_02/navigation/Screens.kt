package com.example.ld_02.navigation

const val MOVIE_KEY = "id"

sealed class Screens(val route: String) {
    object Home: Screens(route =  "home_screen")
    object Favorites: Screens(route = "favorites_screen")
    object Detail : Screens(route = "detail_screen/{$MOVIE_KEY}") {
        fun passId(id: String) : String {
            return "detail_screen/$id"
        }
    }
}
