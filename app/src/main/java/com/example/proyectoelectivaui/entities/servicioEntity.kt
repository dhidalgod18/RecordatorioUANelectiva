package com.example.proyectoelectivaui.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "servicio")
data class servicioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombreServicio")val nombreServicio: String,
    @ColumnInfo(name = "fechaPago")val fechaPago: LocalDate,
    @ColumnInfo(name = "userId") val userId: Int
) {

}