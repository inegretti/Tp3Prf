package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterEjercicio
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class RutinaDetail : Fragment() {

    private lateinit var v: View
    private lateinit var t:TextView
    private lateinit var r:RecyclerView
    private lateinit var ejAdap:AdapterEjercicio
    private lateinit var btn:Button

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
        v=  inflater.inflate(R.layout.fragment_rutina_detail, container, false)
        t=v.findViewById(R.id.tituloRutinaDetail)
        r=v.findViewById(R.id.rutinaDet)
        btn=v.findViewById(R.id.btnAGR)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE

        return v
    }

    override fun onStart() {
        super.onStart()
        usuario = UsuarioSingleton.getInstance()
        //var z=RutinaDetailArgs.fromBundle(requireArguments())
        if(viewModel.z.clearence){
            btn.setVisibility(View.VISIBLE)

        }else{
            btn.setVisibility(View.INVISIBLE)
        }
        t.text=usuario.semana[viewModel.z.posicion].nombre
        ejAdap=AdapterEjercicio(viewModel.z.usuario.semana[viewModel.z.posicion].rutina){position->
            //val rut = z.usuario.semana[position]
            //Snackbar.make(v, "prueba ${rut.rutina.get(0).nombre}", Snackbar.LENGTH_LONG).show()
            var ej= viewModel.z.usuario.semana[viewModel.z.posicion].rutina[position]

            val action = RutinaDetailDirections.actionRutinaDetailToEjercicioDetail(viewModel.z.usuario, viewModel.z.clearence, ej, viewModel.z.posicion)
            findNavController().navigate(action)
        }
        r.layoutManager= LinearLayoutManager(context)
        r.adapter=ejAdap
        btn.setOnClickListener() {
            Log.d("prueba:","llego hasta el boton")
            val action = RutinaDetailDirections.actionRutinaDetailToAgregarEjercicioFragment2(viewModel.z.usuario,viewModel.z.posicion)
            findNavController().navigate(action)
        }
    }

}