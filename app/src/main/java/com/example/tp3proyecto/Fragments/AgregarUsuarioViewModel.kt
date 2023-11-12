package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel

class AgregarUsuarioViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun correoVal(correo: String): Boolean {
        val patronCorreo = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return patronCorreo.matches(correo)
    }
    fun passVal(password: String): Boolean {
        val patronPassword = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}\$")
        return patronPassword.matches(password)
    }



}