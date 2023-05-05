package com.example.proyectoelectivaui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyectoelectivaui.R
import com.example.proyectoelectivaui.databinding.FragmentLoginFragmentoBinding
import com.example.proyectoelectivaui.databinding.FragmentRegistroBinding
import com.example.proyectoelectivaui.entities.usuarioEntity


class RegistroFragment : Fragment() {

    private var _binding : FragmentRegistroBinding? = null

    private val binding get() = _binding!!

    private lateinit var miViewModel: RegistroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registroFragment_to_loginFragmento)
        }
        miViewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)

        binding.btnRegistrarse.setOnClickListener {
            val user = binding.txtUser.text.toString()
            val pass = binding.txtPassword.text.toString()

            val usuario = usuarioEntity(
                user = user,
                password = pass
            )

            miViewModel.agregar(usuario)
        }


    }
}