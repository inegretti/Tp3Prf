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
import com.example.tp3proyecto.R
import com.example.tp3proyecto.Repository.Repositorio
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var email: EditText
    lateinit var contraseña: EditText
    lateinit var btn: Button
    var lista2:Repositorio=Repositorio()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        email = v.findViewById(R.id.email)
        contraseña = v.findViewById(R.id.contraseña)
        btn = v.findViewById(R.id.button2)

        return v
    }

    override fun onStart() {
        super.onStart()
        btn.setOnClickListener() {
            var e:String= email.text.toString()
            var usuario=lista2.lista.find {it.Email==e}
            Snackbar.make(v, "usuario ${usuario}", Snackbar.LENGTH_LONG).show()
            if(usuario!=null){
                if(contraseña.text.toString() == usuario.password){

                    if(usuario.name=="Admin"){
                        val action = LoginFragmentDirections.actionLoginFragmentToAdminFragment(lista2)
                        Log.d("hola= ",lista2.toString())
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
