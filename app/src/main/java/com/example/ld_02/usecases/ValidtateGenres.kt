package com.example.ld_02.usecases

import com.example.ld_02.models.ListItemSelectable

class ValidtateGenres {

    fun execute(input: List<ListItemSelectable>, err: String): ValidationResult {
        if (input.isEmpty()) {
            return ValidationResult(successful = false, errorMessage = err)
        }
        return ValidationResult(successful = true)
    }
}