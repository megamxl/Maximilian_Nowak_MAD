package com.example.ld_02

import com.example.ld_02.models.ListItemSelectable

sealed class AddMovieFormEvent {
    data class TitleInputChanged(val input : String): AddMovieFormEvent()
    data class YearInputChanged(val input : String): AddMovieFormEvent()
    data class DirectorInputChanged(val input : String): AddMovieFormEvent()
    data class ActorsInputChanged(val input : String): AddMovieFormEvent()
    data class PlotInputChanged(val input : String): AddMovieFormEvent()
    data class RatingInputChanged(val input : String): AddMovieFormEvent()
    data class GenreInputChanged(val input: List<ListItemSelectable>): AddMovieFormEvent()
    object Submit : AddMovieFormEvent()
}
