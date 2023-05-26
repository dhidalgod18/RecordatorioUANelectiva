package com.example.proyectoelectivaui.ui.calcular

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.proyectoelectivaui.database.usuarioDataBase
import com.example.proyectoelectivaui.entities.servicioEntity

class CalcularRegistrarViewModel(application: Application): AndroidViewModel(application) {
    private val db = usuarioDataBase.getInstance(getApplication<Application>().applicationContext)
    private val _listaServicios: MediatorLiveData<List<servicioEntity>> = MediatorLiveData()
    val listaServicios: LiveData<List<servicioEntity>> get() = _listaServicios

    private var userId: Int = 0

    fun setUserId(userId: Int) {
        this.userId = userId
        refreshListaServicios()
    }

    fun refreshListaServicios() {
        Log.d("ViewModel", "Refreshing listaServicios for userId: $userId")
        val sourceLiveData = db.servicioDAO().obtenerServiciosUsuarioActual(userId)
        _listaServicios.removeSource(sourceLiveData)
        _listaServicios.addSource(sourceLiveData) { servicios ->
            _listaServicios.value = servicios
            Log.d("ViewModel", "Retrieved ${servicios.size} servicios for userId: $userId")
        }
    }

}