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
         lista.add(Usuario(1,"admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin3","12345","admins@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin3","12345","admins@gmail.com","41444444,",0.0,1.8))
         lista.add(Usuario(1,"admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin3","12345","admins@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin3","12345","admins@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin3","12345","admins@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin","12345","admin@gmail.com","41444444",0.0,1.8))
         lista.add(Usuario(1,"admin3","12345","admins@gmail.com","41444444",0.0,1.8))
     }


    fun agregar(usuario:Usuario){
        lista.add(usuario)
    }


}