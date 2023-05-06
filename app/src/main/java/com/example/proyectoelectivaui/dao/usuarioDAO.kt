package com.example.proyectoelectivaui.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoelectivaui.entities.usuarioEntity

@Dao
interface usuarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(usuario: usuarioEntity) : Long

    @Query("SELECT * FROM usuario WHERE user = :userName AND password = :password LIMIT 1")
    suspend fun getUser(userName: String, password: String) : usuarioEntity?

}