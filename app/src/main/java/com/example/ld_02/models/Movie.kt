package com.example.ld_02.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val year: String,
    val genre: List<Genre>,
    val director: String,
    val actors: String,
    val plot: String,
    val images: List<String>,
    val rating: Float = 0f,
    var isFavorite: Boolean = false
)