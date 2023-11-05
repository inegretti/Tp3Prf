package com.example.tp3proyecto.Fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Repository.Repositorio

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var lista2: Repositorio = Repositorio()
    var usuario:Usuario=Usuario(0,"","vacio","vacio","",1.8,90.0)

    fun validarUs(us:String,pass:String):Usuario?{
       var bus =lista2.lista.find {it.Email==us}
        if(bus!=null){
            usuario=bus
        }
        return usuario
    }

    fun validarPass(pass:String):Boolean{
        return (usuario.password==pass)
    }
}