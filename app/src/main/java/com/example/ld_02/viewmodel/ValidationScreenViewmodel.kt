package com.example.ld_02.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ld_02.AddMovieFormEvent
import com.example.ld_02.states.AddMovieValidation
import com.example.ld_02.usecases.ValidtateGenres
import com.example.ld_02.usecases.ValidtateSimpleInput
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ValidationScreenViewmodel(
    private val validatSimpleInput: ValidtateSimpleInput = ValidtateSimpleInput(),
    private val validtateGenres: ValidtateGenres = ValidtateGenres()
) : ViewModel() {

    var state by mutableStateOf(AddMovieValidation())


    private val _validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = _validationEventChannel.receiveAsFlow()

    fun onEvent(event: AddMovieFormEvent) {
        when (event) {
            is AddMovieFormEvent.GenreInputChanged -> {
                state = state.copy(genres = event.input)
            }
            is AddMovieFormEvent.Submit -> {
                submitData(true)
            }
            is AddMovieFormEvent.ActorsInputChanged -> {
                state = state.copy(actors = event.input)
            }
            is AddMovieFormEvent.DirectorInputChanged -> {
                state = state.copy(director = event.input)
            }
            is AddMovieFormEvent.PlotInputChanged -> {
                state = state.copy(plot = event.input)
            }
            is AddMovieFormEvent.RatingInputChanged -> {
                state = state.copy(rating = event.input)
            }
            is AddMovieFormEvent.TitleInputChanged -> {
                state = state.copy(tile = event.input)
            }
            is AddMovieFormEvent.YearInputChanged -> {
                state = state.copy(year = event.input)
            }
        }
        submitData(false)
    }



    private fun submitData(launch: Boolean) {

        val titleResult = validatSimpleInput.execute(state.tile,"The title can't be empty")
        val yearResult = validatSimpleInput.execute(state.year, "The year can't be empty")
        val genresResult = validtateGenres.execute(state.genres, "You must select one genre")
        val directorResult = validatSimpleInput.execute(state.director, "The director can't be empty")
        val actorsResult = validatSimpleInput.execute(state.actors,"The actor's cant be empty")
        var ratingResult = validatSimpleInput.execute(state.rating, "The rating can't be empty")

         val hasErr =  listOf(
            titleResult,
            yearResult,
            genresResult,
            directorResult,
            actorsResult,
            ratingResult
        ).any{ it.errorMessage!=null}

        if (hasErr) {
            state = state.copy(
                tileError = titleResult.errorMessage,
                yearError = yearResult.errorMessage,
                genresError = genresResult.errorMessage,
                directorError = directorResult.errorMessage,
                actorsError = actorsResult.errorMessage,
                ratingError = ratingResult.errorMessage,
            )
            return
        } else {
            state = state.copy(isButtonEnabled = true)
            if (launch) {
                viewModelScope.launch {
                    _validationEventChannel.send(ValidationEvent.Success)
                }
            }
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

}