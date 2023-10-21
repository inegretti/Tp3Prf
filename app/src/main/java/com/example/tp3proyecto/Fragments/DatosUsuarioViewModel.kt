package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton

class DatoUsuarioViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val usuarioSingleton: UsuarioSingleton

    init {
        usuarioSingleton = UsuarioSingleton
    }
    fun getInstance(): Usuario {
        return usuarioSingleton.getInstance()
    }
}