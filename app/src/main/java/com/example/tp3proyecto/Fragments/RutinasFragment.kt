package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterDia
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class RutinasFragment : Fragment() {

    lateinit var v:View
    lateinit var recyclerDias:RecyclerView
    lateinit var adapterD:AdapterDia
    lateinit var titulo:TextView

    private lateinit var viewModel: RutinasViewModel
    private lateinit var usuario: Usuario
    private var clereance : Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(RutinasViewModel::class.java)
        //                            si aca pongo this solo aplicaria a este fragment
        //                            con activity aplica a toda la actividad(lo vuelvo un singleton)
        // TODO: Use the ViewModel
//        viewModel.z=RutinasFragmentArgs.fromBundle(requireArguments())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rutinas, container, false)
        recyclerDias=v.findViewById(R.id.dias)
        titulo=v.findViewById(R.id.rutTit)
        titulo.text="Semana XXX"

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE

        return v
    }

    override fun onStart() {
        super.onStart()
        usuario = UsuarioSingleton.getInstance()
        adapterD= AdapterDia(usuario.semana){position->
            var clear=RutinasFragmentArgs.fromBundle(requireArguments()).clereance
            if(usuario.tieneRutinas(position)) {
                clereance = true

                val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(usuario,position,clear)
                findNavController().navigate(action)


            }else {
                val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(usuario,position,clear)
                findNavController().navigate(action)

            }

        }
        recyclerDias.layoutManager=LinearLayoutManager(context)
        recyclerDias.adapter=adapterD

    }
}