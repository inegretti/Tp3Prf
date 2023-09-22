package com.example.tp3proyecto.Fragments

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
import com.example.tp3proyecto.Adapters.AdapterEjercicio
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class RutinaDetail : Fragment() {



    private lateinit var v: View
    private lateinit var t:TextView
    private lateinit var r:RecyclerView
    private lateinit var ejAdap:AdapterEjercicio
    private lateinit var btn:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=  inflater.inflate(R.layout.fragment_rutina_detail, container, false)
        t=v.findViewById(R.id.tituloRutinaDetail)
        r=v.findViewById(R.id.rutinaDet)
        btn=v.findViewById(R.id.btnAGR)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z=RutinaDetailArgs.fromBundle(requireArguments())
        if(z.clearance){
            btn.setVisibility(View.VISIBLE)

        }else{
            btn.setVisibility(View.INVISIBLE)
        }
        t.text=z.usuario.semana[z.posicion].nombre
        ejAdap=AdapterEjercicio(z.usuario.semana[z.posicion].rutina){position->
            val rut = z.usuario.semana[position]
            //Snackbar.make(v, "prueba ${rut.rutina.get(0).nombre}", Snackbar.LENGTH_LONG).show()
            var ej= z.usuario.semana[z.posicion].rutina[position]

            val action =  RutinaDetailDirections.actionRutinaDetailToEjercicioDetail(z.usuario,z.clearance,ej,z.posicion)
            findNavController().navigate(action)
        }
        r.layoutManager= LinearLayoutManager(context)
        r.adapter=ejAdap
        btn.setOnClickListener() {
            Log.d("prueba:","llego hasta el boton")
            val action = RutinaDetailDirections.actionRutinaDetailToAgregarEjercicioFragment2(z.usuario,z.posicion)
            findNavController().navigate(action)
        }
    }

}