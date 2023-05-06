package com.example.proyectoelectivaui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.proyectoelectivaui.MainActivity2
import com.example.proyectoelectivaui.R
import com.example.proyectoelectivaui.database.usuarioDataBase
import com.example.proyectoelectivaui.databinding.FragmentLoginFragmentoBinding
import kotlinx.coroutines.runBlocking


class LoginFragmento : Fragment() {

    private var _binding: FragmentLoginFragmentoBinding? = null

    private val binding get() = _binding!!

    private val db: usuarioDataBase by lazy {
        usuarioDataBase.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginFragmentoBinding.inflate(inflater, container, false)
        this.binding.btnLogin.setOnClickListener{ login() }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegistrarse.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragmento_to_registroFragment)
        }

    }

    fun login() {
        val userName = binding.txtUser.text.toString()
        val password = binding.txtPassword.text.toString()

        // Check if the user name and password are empty
        if (userName.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Por favor ingrese una combinación valida de usuario y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        val user = runBlocking { db.usuarioDAO().getUser(userName, password) }

        if (user != null) {
            // If the user exists, navigate to MainActivity2
            val loginIntent = Intent(activity, MainActivity2::class.java)
            loginIntent.putExtra("user", user.user)
            startActivity(loginIntent)
        } else {
            // If the user does not exist, show an error message
            Toast.makeText(context, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show()
        }

    }

}