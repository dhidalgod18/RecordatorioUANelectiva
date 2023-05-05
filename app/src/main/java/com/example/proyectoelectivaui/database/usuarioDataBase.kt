package com.example.proyectoelectivaui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectoelectivaui.dao.usuarioDAO
import com.example.proyectoelectivaui.entities.usuarioEntity

@Database(entities = [usuarioEntity::class], version = 1)
abstract class usuarioDataBase : RoomDatabase(){
    abstract fun usuarioDAO(): usuarioDAO

    companion object{
        private lateinit var instance: usuarioDataBase
        fun getInstance(context: Context): usuarioDataBase{
            instance = Room.databaseBuilder(context,usuarioDataBase::class.java, "usuarioDataBase.db" ).build()
            return instance
        }
    }
}