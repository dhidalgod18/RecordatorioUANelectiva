package com.example.proyectoelectivaui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.proyectoelectivaui.R
import com.example.proyectoelectivaui.entities.servicioEntity

class servicioAdapter(val context: Context, val servicios: List<servicioEntity>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.lista_servicio, parent, false)

        val servicio = servicios[position]

        val name = view.findViewById<TextView>(R.id.name)
        name.text = servicio.nombreServicio // Aquí estableces el nombre del servicio

        return view
    }

    override fun getItem(position: Int): Any {
        return servicios[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return servicios.size
    }
}