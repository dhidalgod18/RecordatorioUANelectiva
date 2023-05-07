package com.example.proyectoelectivaui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.proyectoelectivaui.dao.servicioDAO
import com.example.proyectoelectivaui.dao.usuarioDAO
import com.example.proyectoelectivaui.entities.servicioEntity
import com.example.proyectoelectivaui.entities.usuarioEntity
import com.example.proyectoelectivaui.ui.RegistrarServicio.Convertir

@Database(entities = [usuarioEntity::class, servicioEntity::class], version = 1)
@TypeConverters(Convertir::class)
abstract class usuarioDataBase : RoomDatabase(){
    abstract fun usuarioDAO(): usuarioDAO
    abstract fun servicioDAO(): servicioDAO

    companion object{
        private lateinit var instance: usuarioDataBase
        fun getInstance(context: Context): usuarioDataBase{
            instance = Room.databaseBuilder(context,usuarioDataBase::class.java, "usuarioDataBase.db" ).build()
            return instance
        }
    }
}