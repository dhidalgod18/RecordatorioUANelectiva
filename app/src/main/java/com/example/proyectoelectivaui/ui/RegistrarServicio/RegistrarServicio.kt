package com.example.proyectoelectivaui.ui.RegistrarServicio

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoelectivaui.databinding.FragmentRegistrarServicioBinding
import com.example.proyectoelectivaui.entities.servicioEntity
import java.time.LocalDate
import java.util.Calendar

class RegistrarServicio : Fragment() {

    private var _binding : FragmentRegistrarServicioBinding? = null

    private val binding get() = _binding!!

    private lateinit var miViewModel: RegistrarServicioViewModel
    lateinit var fechaPago: LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrarServicioBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        miViewModel = ViewModelProvider(this).get(RegistrarServicioViewModel::class.java)
        binding.btnFechaPago.setOnClickListener {
            // Obtén la fecha actual para mostrarla en el DatePicker por defecto
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Crea el componente DatePickerDialog con la fecha actual
            val datePicker = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                // Guarda la fecha seleccionada en la variable
                fechaPago = LocalDate.of(year, month + 1, dayOfMonth)

                // Actualiza el texto del botón con la fecha seleccionada
                binding.btnFechaPago.text = fechaPago.toString()
            }, year, month, day)

            // Muestra el DatePickerDialog
            datePicker.show()
        }
        binding.btnGuardar.setOnClickListener {

            val nombre = binding.textNombreServicio.text.toString()
            val pago = binding.textValorPagar.text.toString()
            val valorPagar = pago.toDouble()

            val servicio = servicioEntity(
                nombreServicio = nombre,
                valorPagar = valorPagar,
                fechaPago = fechaPago
            )
            miViewModel.agregarServicio(servicio) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(
                        requireContext(),
                        "Se ha registrado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.textNombreServicio.setText("")
                    binding.textValorPagar.setText("")
                    binding.btnFechaPago.setText("")
                    binding.btnFechaPago.text = "Seleccionar fecha de pago"
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Hubo un error durante el registro",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }
}