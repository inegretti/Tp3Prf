package com.example.tp3proyecto.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class UserFragment : Fragment() {

    lateinit var v:View
    lateinit var txt: TextView
    lateinit var btnRut:Button
    lateinit var btnCon:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_user, container, false)
        txt=v.findViewById(R.id.txtusuario)
        btnRut=v.findViewById(R.id.btnRut)
        btnCon=v.findViewById(R.id.btnConfig)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z = UserFragmentArgs.fromBundle(requireArguments())
        txt.text="Bienvenido ${z.usuario.name} }"
        btnRut.setOnClickListener() {
            //Snackbar.make(v, "proximamente", Snackbar.LENGTH_LONG).show()
            val action =  UserFragmentDirections.actionUserFragmentToRutinasFragment(z.usuario,false)
            findNavController().navigate(action)
            Snackbar.make(v, "En construccion", Snackbar.LENGTH_LONG).show()
        }
        btnCon.setOnClickListener(){
            Snackbar.make(v, "proximamente", Snackbar.LENGTH_LONG).show()
        }
    }

}