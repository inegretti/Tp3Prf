package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var email: EditText
    lateinit var contraseña: EditText
    lateinit var btn: Button
    var lista= mutableListOf<Usuario>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        email = v.findViewById(R.id.email)
        contraseña = v.findViewById(R.id.contraseña)
        btn = v.findViewById(R.id.button2)
        lista.add(Usuario(1, "Admin", "12345", "admin@gmail.com", "dsfdfds"))
        var us:Usuario
        us = Usuario(2, "Juan", "juana2", "juan@gmail.com", "dsfdfds")
        us.agregarARutina(0, "flexiones x 5")
        lista.add(us)

        Log.d("revision:", us.semana.get(0).rutina.toString())
        return v
    }

    override fun onStart() {
        super.onStart()
        btn.setOnClickListener() {
            var e:String= email.text.toString()

            var usuario=lista.find {it.Email==e}
            Snackbar.make(v, "usuario ${usuario}", Snackbar.LENGTH_LONG).show()
            if(usuario!=null){
                if(contraseña.text.toString() == usuario.password){

                    if(usuario.name=="Admin"){
                        val action = LoginFragmentDirections.actionLoginFragmentToAdminFragment()
                       findNavController().navigate(action)
                       // findNavController().navigateUp()

                    }else{
                        val action = LoginFragmentDirections.actionLoginFragmentToUserFragment(usuario)
                        findNavController().navigate(action)
                    }


                    Snackbar.make(v, "Bienvenido ${usuario.name}", Snackbar.LENGTH_LONG).show()
                }else{
                    Snackbar.make(v, "Contraseña incorrecta", Snackbar.LENGTH_LONG).show()
                }

            }else{
                Snackbar.make(v, "No existe un usuario registrado con ese mail", Snackbar.LENGTH_LONG).show()
            }


        }

    }
}