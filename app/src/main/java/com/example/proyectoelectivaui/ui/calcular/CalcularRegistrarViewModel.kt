package com.example.proyectoelectivaui.ui.calcular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.proyectoelectivaui.database.usuarioDataBase
import com.example.proyectoelectivaui.entities.servicioEntity

class CalcularRegistrarViewModel(application: Application): AndroidViewModel(application) {
    private val db = usuarioDataBase.getInstance(getApplication<Application>().applicationContext)
    val listaServicios: LiveData<List<servicioEntity>> = db.servicioDAO().obtenerTodosLosServicios()


}