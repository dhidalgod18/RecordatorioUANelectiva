package com.example.proyectoelectivaui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoelectivaui.database.usuarioDataBase
import com.example.proyectoelectivaui.entities.usuarioEntity
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application): AndroidViewModel(application){
    private val db = usuarioDataBase.getInstance(getApplication<Application>().applicationContext)


    fun agregar(user: usuarioEntity) {
        viewModelScope.launch{
            db.usuarioDAO().saveUser(user)
        }
    }



}