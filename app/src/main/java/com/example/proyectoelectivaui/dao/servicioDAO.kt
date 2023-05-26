package com.example.proyectoelectivaui.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoelectivaui.entities.servicioEntity

@Dao
interface servicioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveServicio(servicio: servicioEntity) : Long

    @Query("SELECT * FROM servicio")
    fun obtenerTodosLosServicios(): LiveData<List<servicioEntity>>

    @Query("SELECT * FROM servicio WHERE userId = :userId")
    fun obtenerServiciosUsuarioActual(userId: Int): LiveData<List<servicioEntity>>
}