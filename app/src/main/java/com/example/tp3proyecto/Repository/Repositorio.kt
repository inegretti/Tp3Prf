package com.example.tp3proyecto.Repository

import android.os.Parcelable
import com.example.tp3proyecto.Entidades.Ejercicio
import com.example.tp3proyecto.Entidades.RegistroPeso
import com.example.tp3proyecto.Entidades.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.*

@Parcelize
class Repositorio():Parcelable{


    var lista:MutableList<Usuario> = mutableListOf()
    var listaE:MutableList<Ejercicio> = mutableListOf()
    val database = Firebase.firestore

    companion object {
        var listaA:MutableList<Usuario> = mutableListOf()
        var listaC:MutableList<Ejercicio> = mutableListOf()
    }

     init{
         database.collection("users").get()
             .addOnSuccessListener {
                 //var lista2=it.toObjects<Usuario>().toMutableList()//
                 for(Usuario in it.toObjects<Usuario>()){
                     lista.add(Usuario)
                     listaA.add(Usuario)
                 }
             }
         database.collection("ejercicios").get()
             .addOnSuccessListener {
                 //var lista2=it.toObjects<Ejercicio>().toMutableList()//
                 for(Ejercicio in it.toObjects<Ejercicio>()){
                     listaE.add(Ejercicio)
                     listaC.add(Ejercicio)
                 }
             }

     }




    fun agregar(usuario:Usuario){
        lista.add(usuario)
    }

    fun finalizarConexion(){
        database.terminate()
    }


}