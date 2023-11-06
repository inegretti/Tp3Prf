package com.example.tp3proyecto.Entidades

object UsuarioSingleton {
    private var userInstance: Usuario? = null

    fun getInstance(): Usuario {
        if (userInstance == null) {
            userInstance = Usuario(
                id = 0,
                name = "Nombre",
                password = "Contraseña",
                Email = "email@example.com",
                imgUrl = "URL de la imagen",
                pesoActual = 0.0,
                altura = 0.0
            )
        }
        return userInstance!!
    }

    fun setUsuario(usuario: Usuario) {
        userInstance = usuario
    }
}