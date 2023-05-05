package com.example.proyectoelectivaui.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.proyectoelectivaui.entities.usuarioEntity

@Dao
interface usuarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(usuario: usuarioEntity)

}