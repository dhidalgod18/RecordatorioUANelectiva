package com.example.proyectoelectivaui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoelectivaui.databinding.ListaServicioBinding
import com.example.proyectoelectivaui.entities.servicioEntity

class servicioAdapter : RecyclerView.Adapter<servicioAdapter.ServicioViewHolder>() {

    private var servicios: List<servicioEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListaServicioBinding.inflate(inflater, parent, false)
        return ServicioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicioViewHolder, position: Int) {
        val servicio = servicios[position]
        holder.bind(servicio)
    }

    override fun getItemCount(): Int = servicios.size

    fun submitList(newList: List<servicioEntity>) {
        servicios = newList
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): servicioEntity {
        return servicios[position]
    }

    inner class ServicioViewHolder(private val binding: ListaServicioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(servicio: servicioEntity) {
            binding.name.text = servicio.nombreServicio
        }
    }
}