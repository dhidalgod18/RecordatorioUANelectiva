package com.example.proyectoelectivaui.ui.calcular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoelectivaui.R
import com.example.proyectoelectivaui.adapter.servicioAdapter
import com.example.proyectoelectivaui.databinding.FragmentCalcularBinding
import com.example.proyectoelectivaui.entities.servicioEntity

class Calcular : Fragment() {
    private var _binding : FragmentCalcularBinding? = null
    private val binding get() = _binding!!
    private lateinit var listView: ListView
    private lateinit var miViewModel: CalcularRegistrarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalcularBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.lista_servicios)
        miViewModel = ViewModelProvider(this).get(CalcularRegistrarViewModel::class.java)

        val observer = Observer<List<servicioEntity>> { listaServicios ->
            val adaptador = servicioAdapter(requireContext(), listaServicios)
            listView.adapter = adaptador
        }

        miViewModel.listaServicios.observe(viewLifecycleOwner, observer)

        listView.setOnItemClickListener { parent, view, position, id ->
            val servicio = parent.getItemAtPosition(position) as servicioEntity
            Toast.makeText(context, servicio.nombreServicio, Toast.LENGTH_SHORT).show()
        }
    }
    
}