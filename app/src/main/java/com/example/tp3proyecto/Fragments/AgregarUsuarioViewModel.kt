package com.example.tp3proyecto.Fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tp3proyecto.Entidades.Usuario

class AgregarUsuarioViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun validarCampos(nombre: String, contrasenia: String, confirmacion: String, altura: String, peso: String, email: String): Boolean {
        val camposVacios = listOf(nombre, contrasenia, confirmacion, altura, peso, email).any { it.isEmpty() }
        Log.d("AgregarUsuarioViewModel", "camposVacios: $camposVacios")
        if (camposVacios) {
            return false
        }

        return true
    }

    fun validarAlturaPeso(altura: Double, peso: Double): Boolean {
        return altura > 1 && peso > 10
    }

    fun usuarioExistente(email: String, usuarios: List<Usuario>): Boolean {
        return usuarios.any { it.Email == email }
    }

    fun contraseniasCoinciden(contrasenia: String, confirmacion: String): Boolean {
        return contrasenia == confirmacion
    }

    fun correoVal(correo: String): Boolean {
        val patronCorreo = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return patronCorreo.matches(correo)
    }
    fun passVal(password: String): Boolean {
        val patronPassword = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}\$")
        return patronPassword.matches(password)
    }



}

