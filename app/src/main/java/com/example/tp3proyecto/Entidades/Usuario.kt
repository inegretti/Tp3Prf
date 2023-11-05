package com.example.tp3proyecto.Entidades

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

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
    constructor() : this(0, "", "","","",99.0,22.0)
    var semana= mutableListOf<Dia>(Dia("Lunes"),Dia("Martes"),Dia("Miercoles"),
        Dia("Jueves"),Dia("Viernes"),Dia("Sabado"),Dia("Domingo"))
    var historialPeso: MutableList<RegistroPeso> = mutableListOf()

    init {
        val r= LocalDateTime.now()
        historialPeso.add(RegistroPeso(pesoActual, Date(r.year,r.month.value,r.dayOfMonth)))

    }

    fun agregarARutina(dia:Int,ejercicio:Ejercicio){
        semana[dia].agregarEjercicio(ejercicio)

    }
}
