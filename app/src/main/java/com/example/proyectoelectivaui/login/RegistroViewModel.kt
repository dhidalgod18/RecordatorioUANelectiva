package com.example.proyectoelectivaui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoelectivaui.database.usuarioDataBase
import com.example.proyectoelectivaui.entities.usuarioEntity
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application): AndroidViewModel(application){
    private val db = usuarioDataBase.getInstance(getApplication<Application>().applicationContext)


    fun agregar(user: usuarioEntity, callback: (Boolean) -> Unit) {
        viewModelScope.launch{
            val existingUser = db.usuarioDAO().getUser(user.user, user.password)
            if (existingUser == null) {
                val result = db.usuarioDAO().saveUser(user)
                val isSuccess = result > 0
                callback(isSuccess)
            } else {
                callback(false)
            }
        }
    }
}