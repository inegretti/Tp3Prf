package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel
import com.example.tp3proyecto.Entidades.Ejercicio

class AgregarEjNewViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun validarCampos(nombre: String, mult: String): Boolean {
        return !nombre.isEmpty() && !mult.isEmpty()
    }

    fun ejercicioExistente(nombre: String, listaEjercicios: List<Ejercicio>): Boolean {
        return listaEjercicios.any { it.nombre == nombre.uppercase() }
    }
}