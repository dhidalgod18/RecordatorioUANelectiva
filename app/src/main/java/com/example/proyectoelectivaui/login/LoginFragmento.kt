package com.example.proyectoelectivaui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectoelectivaui.R
import com.example.proyectoelectivaui.databinding.FragmentLoginFragmentoBinding


class LoginFragmento : Fragment() {

    private var _binding: FragmentLoginFragmentoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login_fragmento, container, false)
        _binding = FragmentLoginFragmentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegistrarse.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragmento_to_registroFragment)
        }

        /*binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.)
        }*/
    }



}