package com.example.tp3proyecto.Entidades
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
class Ejercicio(
    var id:Int,
    var nombre:String,
    var media:String,
    var imgUrl : String
) : Parcelable {
    var series:Int
    var repeticiones:Int
    var descripcion:String
    var comentario:String
    var estado:Boolean


    init {
        series=0
        repeticiones=0
        descripcion=""
        comentario=""
        estado=false

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

    fun cambiarDescripcion(descNueva:String){
        if(descNueva.isNotEmpty()){
            descripcion=descNueva
        }
    }
}