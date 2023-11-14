package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterEjercicio
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class RutinaDetail : Fragment() {

    private lateinit var v: View
    private lateinit var titulo:TextView
    private lateinit var r:RecyclerView
    private lateinit var ejAdap:AdapterEjercicio
    private lateinit var btnAgregar:Button

    private lateinit var viewModel: RutinaDetailViewModel
    private lateinit var usuario: Usuario
//    private var clearance : Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(RutinaDetailViewModel::class.java)
        //                            si aca pongo this solo aplicaria a este fragment
        //                            con activity aplica a toda la actividad(lo vuelvo un singleton)
        // TODO: Use the ViewModel
        viewModel.z=RutinaDetailArgs.fromBundle(requireArguments())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_rutina_detail, container, false)
        titulo=v.findViewById(R.id.tituloRutinaDetail)
        r=v.findViewById(R.id.rutinaDet)
        btnAgregar=v.findViewById(R.id.btnAgregarEj)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE

        return v
    }

    override fun onStart() {
        super.onStart()
        usuario = UsuarioSingleton.getInstance()
        if(viewModel.z.clearence){
            btnAgregar.setVisibility(View.VISIBLE)

        }else{
            btnAgregar.setVisibility(View.INVISIBLE)
        }
        titulo.text=usuario.semana[viewModel.z.posicion].nombre
        ejAdap=AdapterEjercicio(viewModel.z.usuario.semana[viewModel.z.posicion].rutina){position->

            var ej= viewModel.z.usuario.semana[viewModel.z.posicion].rutina[position]

            val action = RutinaDetailDirections.actionRutinaDetailToEjercicioDetail(viewModel.z.usuario, viewModel.z.clearence, ej, viewModel.z.posicion)
            findNavController().navigate(action)
        }
        r.layoutManager= LinearLayoutManager(context)
        r.adapter=ejAdap
        btnAgregar.setOnClickListener() {
            val action = RutinaDetailDirections.actionRutinaDetailToAgregarEjercicioFragment2(viewModel.z.usuario,viewModel.z.posicion)
            findNavController().navigate(action)
        }
    }

}