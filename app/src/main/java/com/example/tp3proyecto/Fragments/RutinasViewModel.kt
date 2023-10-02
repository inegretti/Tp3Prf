package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel

class RutinasViewModel : ViewModel() {
    lateinit var z:RutinasFragmentArgs
    // TODO: Implement the ViewModel

    fun tieneRutinas(pos:Int):Boolean{
        return z.usuario.semana[pos].rutina.isEmpty()
    }
}