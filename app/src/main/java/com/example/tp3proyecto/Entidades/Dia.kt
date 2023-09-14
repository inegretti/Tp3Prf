package com.example.tp3proyecto.Entidades

import java.util.Date

data class Dia(
    var nombre:String,

){
    var rutina= mutableListOf<Ejercicio>()
    var estado:Boolean

    init {
        estado=false
    }

    fun agregarEjercicio(ej:Ejercicio){
        rutina.add((ej))
    }
}
