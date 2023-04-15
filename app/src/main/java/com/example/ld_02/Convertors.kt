package com.example.ld_02

import androidx.room.TypeConverter
import com.example.ld_02.models.Genre

class Convertors {

    @TypeConverter
    fun listOfStringToString(value: List<String>) = value.joinToString("\\")

    @TypeConverter
    fun stringToListOfString(value: String) = value.split("\\").map { it.trim() }

    @TypeConverter
    fun listOfGenreToString(value: List<Genre>): String {
        var st = ""
        for (enum in value) {
            st += "${enum.name},"
        }
        return st
    }

    @TypeConverter
    fun stringToListOfGenre(value: String): List<Genre> {
        var list: MutableList<Genre> = mutableListOf()
        value.split(",").map {
            if (it.isNotBlank()) {
                list.add(Genre.valueOf(it))
            }
        }
        return list.toList()
    }

}

