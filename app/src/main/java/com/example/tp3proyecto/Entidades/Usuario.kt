package com.example.tp3proyecto.Entidades

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    var id:Int,
    var name:String,
    var password:String,
    var Email:String,
    var imgUrl:String,
    var pesoActual:Double,
    var altura:Double
                   ):Parcelable{
    var semana= mutableListOf<Dia>(Dia("Lunes"),Dia("Martes"),Dia("Miercoles"),
        Dia("Jueves"),Dia("Viernes"),Dia("Sabado"),Dia("Domingo"))
    var historialPeso: MutableList<Double> = mutableListOf()
    init {
        historialPeso.add(pesoActual)

    }

    fun agregarARutina(dia:Int,ejercicio:String){
        semana[dia].agregarEjercicio(ejercicio)

    }
}
