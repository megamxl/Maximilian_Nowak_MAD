package com.example.ld_02.states

import com.example.ld_02.models.ListItemSelectable

data class AddMovieValidation(

    val tile: String = "",
    val tileError: String? = null,
    val year: String = "",
    val yearError: String? = null,
    val genres: List<ListItemSelectable> = emptyList(),
    val genresError: String? = null,
    val director: String = "",
    val directorError: String? = null,
    val actors: String = "",
    val actorsError: String? = null,
    val plot: String = "",
    val plotError: String? = null,
    val rating: String = "",
    val ratingError: String? = null,
    val isButtonEnabled: Boolean = false
)
