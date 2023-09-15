package com.example.tp3proyecto.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterDia
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class RutinasFragment : Fragment() {

    lateinit var v: View
    lateinit var d:RecyclerView
    lateinit var adapterD:AdapterDia
    lateinit var tit:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rutinas, container, false)
        d=v.findViewById(R.id.dias)
        tit=v.findViewById(R.id.rutTit)
        tit.text="Semana"
        return v
    }

    override fun onStart() {
        super.onStart()
        var z = RutinasFragmentArgs.fromBundle(requireArguments())
        adapterD= AdapterDia(z.usuario.semana){position->
            Snackbar.make(v,"posicion ${position}",Snackbar.LENGTH_LONG).show()
            val rut = z.usuario.semana[position]
           if(rut.rutina.isEmpty()) {
               Snackbar.make(v, "no hay rutina registrada", Snackbar.LENGTH_LONG).show()
               // val rut = z.usuario.semana[position]
               // Snackbar.make(v, "prueba ${rut.rutina.isEmpty()}", Snackbar.LENGTH_LONG).show()
               // val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(z.usuario,position)
               // findNavController().navigate(action)
            }else {
               val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(z.usuario,position,z.clereance)
               findNavController().navigate(action)
           }

        }
        d.layoutManager=LinearLayoutManager(context)
        d.adapter=adapterD

    }
}