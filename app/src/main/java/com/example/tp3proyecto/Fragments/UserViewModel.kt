package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModel
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton

class UserViewModel : ViewModel() {

    private  var usuarioSingleton: Usuario = UsuarioSingleton.getInstance()
    // TODO: Implement the ViewModel
}