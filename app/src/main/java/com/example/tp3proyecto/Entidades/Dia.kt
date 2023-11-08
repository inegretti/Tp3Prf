package com.example.tp3proyecto.Entidades

data class Dia(
    var nombre:String,

){
    constructor() : this("")
    var rutina= mutableListOf<Ejercicio>()
    var estado:Boolean

    init {
        estado=false
    }

    fun agregarEjercicio(ej:Ejercicio){
        rutina.add((ej))
    }
}
