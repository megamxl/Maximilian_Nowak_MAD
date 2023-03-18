package com.example.ld_02

import com.example.ld_02.models.Movie
import com.example.ld_02.models.getMovieById

object FavoriteListSingleton {

    private var favMoveis: List<Movie> = mutableListOf()

    fun addMovie(movieID: String) {
        favMoveis =  favMoveis + listOf(getMovieById(movieID))
    }

    fun clear() {
        favMoveis = emptyList()
    }

    fun removeMovie(movieId: String) {
        favMoveis = favMoveis.filter { it.id != movieId }
    }

    fun isInList(movieId: String): Boolean {
        return favMoveis.filter { it.id == movieId }.size == 1
    }

    fun getFavorites(): List<Movie> {
        return favMoveis
    }


}