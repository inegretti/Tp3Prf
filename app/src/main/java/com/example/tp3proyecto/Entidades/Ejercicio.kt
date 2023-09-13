package com.example.tp3proyecto.Entidades

class Ejercicio(
    var id:Int,
    var nombre:String,
    var media:String
) {
    var series:Int
    var repeticiones:Int

    init {
        series=0
        repeticiones=0
    }

    fun cambiarSerie(serie:Int){
        if(serie>0){
            series=serie
        }
    }

    fun cambiarRepeticiones(repeticion:Int){
        if(repeticion>0){
            repeticiones=repeticion
        }
    }
}