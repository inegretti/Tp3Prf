package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterUsuario
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class AdminFragment : Fragment() {

    lateinit var v:View
    lateinit var r:RecyclerView
    lateinit var t:TextView
    lateinit var adapter:AdapterUsuario
    lateinit var btn:Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_admin, container, false)
        t=v.findViewById(R.id.textAdm)
        r=v.findViewById(R.id.adminView)
        t.text="Administrador de Usuarios"
        btn=v.findViewById(R.id.btnLgAd)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z = AdminFragmentArgs.fromBundle(requireArguments())
        //abro funcion con {} como metodo
        adapter= AdapterUsuario(z.usuarios.lista){
                // es lo que envia el adapter
                position->
            Snackbar.make(v,"click en ${position}",Snackbar.LENGTH_LONG).show()

        }
        //configurar
        r.layoutManager=LinearLayoutManager(context)
        r.adapter=adapter

        btn.setOnClickListener(){
            findNavController().navigateUp()
            Snackbar.make(v,"Adios Admin",Snackbar.LENGTH_LONG).show()
        }

    }



}