package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton

class RutinasViewModel : ViewModel() {
    private lateinit var z: RutinasFragmentArgs
    private val usuarioSingleton : UsuarioSingleton

    init {
        usuarioSingleton = UsuarioSingleton
    }
    fun setZ(rutinasFragmentArgs: RutinasFragmentArgs) {
        z = rutinasFragmentArgs
    }

    fun getZ(): RutinasFragmentArgs {
        return z
    }

    fun tieneRutinas(pos: Int): Boolean {
        return z.usuario.semana[pos].rutina.isEmpty()
    }

    fun getInstance(): Usuario {
        return usuarioSingleton.getInstance()
    }
}

