package com.example.ld_02

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.TypeConverter
import com.example.ld_02.models.Genre
import com.example.ld_02.models.Movie
class Convertors {

    @TypeConverter
    fun listOfStringToString(value: List<String>) = value.joinToString("\\")

    @TypeConverter
    fun stringToListOfString(value: String) = value.split("\\").map{ it.trim()}

    @TypeConverter
    fun listOfGenreToString(value: List<Genre>): String {
        var st = ""
        for (enum in value) {
            st +=  "${enum.name},"
        }
        return st
    }

    @TypeConverter
    fun stringToListOfGenre(value: String): List<Genre> {
        var list : MutableList<Genre> = mutableListOf()
        value.split(",").map {
            if (it.isNotBlank()) {
                list.add(Genre.valueOf(it))
            }
        }
        return list.toList()
    }

}

