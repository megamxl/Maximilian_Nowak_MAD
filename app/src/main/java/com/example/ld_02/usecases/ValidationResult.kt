package com.example.ld_02.usecases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
