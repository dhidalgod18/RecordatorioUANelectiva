package com.example.proyectoelectivaui.ui.RegistrarServicio

import androidx.room.TypeConverter
import java.time.LocalDate

class Convertir {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString)
    }
}