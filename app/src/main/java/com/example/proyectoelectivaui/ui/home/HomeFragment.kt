package com.example.proyectoelectivaui.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectoelectivaui.R
import com.example.proyectoelectivaui.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val userName = activity?.intent?.getStringExtra("user")
        if (!userName.isNullOrBlank()) {
            binding.welcomeText.text = getString(R.string.hola_usuario, userName)
        } else {
            binding.welcomeText.text = getString(R.string.hola_usuario, "")
        }

        return binding.root
    }
}