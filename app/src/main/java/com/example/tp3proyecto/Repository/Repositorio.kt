package com.example.tp3proyecto.Repository

import android.os.Parcelable
import com.example.tp3proyecto.Entidades.Usuario
import kotlinx.parcelize.Parcelize

@Parcelize
class Repositorio():Parcelable{

    var lista:MutableList<Usuario> = mutableListOf()


     init{
         lista.add(Usuario(1,"Admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(2,"Juan","Juan2","juan@gmail.com","41444444",68.0,1.90))
         lista.add(Usuario(3,"pedro","76952","pet@gmail.com","41444444",67.0,1.75))
         lista.add(Usuario(4,"alejo","alejo","ale88@gmail.com","41444444",80.0,1.6))
         lista.add(Usuario(5,"martin","tincho22","martin@gmail.com","41444444",45.0,1.7))
         lista.add(Usuario(6,"Brian","br14n","martin@gmail.com","41444444",90.0,1.86))
     }


    fun agregar(usuario:Usuario){
        lista.add(usuario)
    }


}