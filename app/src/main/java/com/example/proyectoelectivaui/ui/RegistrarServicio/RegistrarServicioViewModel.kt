package com.example.proyectoelectivaui.ui.RegistrarServicio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoelectivaui.database.usuarioDataBase
import com.example.proyectoelectivaui.entities.servicioEntity
import kotlinx.coroutines.launch

class RegistrarServicioViewModel(application: Application): AndroidViewModel(application) {
    private val db = usuarioDataBase.getInstance(getApplication<Application>().applicationContext)

    fun agregarServicio(servicio: servicioEntity, callback: (Boolean) -> Unit) {
        viewModelScope.launch{
            val result = db.servicioDAO().saveServicio(servicio)
            val isSuccess = result > 0
            callback(isSuccess)
        }
    }
}