package com.example.ld_02.usecases

class ValidtateSimpleInput {

    fun execute(input: String, err: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(successful = false, errorMessage = err)
        }
        return ValidationResult(successful = true)
    }
}