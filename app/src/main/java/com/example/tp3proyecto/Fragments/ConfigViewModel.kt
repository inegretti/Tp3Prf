package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
class ConfigViewModel : ViewModel() {

    private val usuarioSingleton = UsuarioSingleton

    fun getInstance(): Usuario {
        return usuarioSingleton.getInstance()
    }
}
