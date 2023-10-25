package com.example.tp3proyecto.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterDia
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class RutinasFragment : Fragment() {

    lateinit var v: View
    lateinit var d:RecyclerView
    lateinit var adapterD:AdapterDia
    lateinit var tit:TextView

    private lateinit var viewModel: RutinasViewModel
    private lateinit var usuario: Usuario

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(RutinasViewModel::class.java)
        //                            si aca pongo this solo aplicaria a este fragment
        //                            con activity aplica a toda la actividad(lo vuelvo un singleton)
        // TODO: Use the ViewModel
        viewModel.z=RutinasFragmentArgs.fromBundle(requireArguments())
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rutinas, container, false)
        d=v.findViewById(R.id.dias)
        tit=v.findViewById(R.id.rutTit)
        tit.text="Semana XXX"

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE

        return v
    }

    override fun onStart() {
        super.onStart()
        //var z = RutinasFragmentArgs.fromBundle(requireArguments())
        usuario = UsuarioSingleton.getInstance()
        adapterD= AdapterDia(viewModel.z.usuario.semana){position->
            Snackbar.make(v,"posicion ${position}",Snackbar.LENGTH_LONG).show()
            //val rut = z.usuario.semana[position]
            //viewModel.z.usuario.semana[position].rutina.isEmpty() ||
           if(viewModel.tieneRutinas(position)) {

               // val rut = z.usuario.semana[position]
               // Snackbar.make(v, "prueba ${rut.rutina.isEmpty()}", Snackbar.LENGTH_LONG).show()
               // val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(z.usuario,position)
               // findNavController().navigate(action)
               if(viewModel.z.clereance){
                   val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(viewModel.z.usuario,position,viewModel.z.clereance)
                   findNavController().navigate(action)
               }else{
                   Snackbar.make(v, "no hay rutina registrada", Snackbar.LENGTH_LONG).show()
               }


            }else {
               val action =  RutinasFragmentDirections.actionRutinasFragmentToRutinaDetail(viewModel.z.usuario,position,viewModel.z.clereance)
               findNavController().navigate(action)
           }

        }
        d.layoutManager=LinearLayoutManager(context)
        d.adapter=adapterD

    }
}