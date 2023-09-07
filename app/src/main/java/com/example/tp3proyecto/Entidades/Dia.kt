package com.example.tp3proyecto.Entidades

import java.util.Date

data class Dia(
    var nombre:String,

){
    var rutina= mutableListOf<String>()

    fun agregarEjercicio(ej:String){
        rutina.add((ej))
    }
}
