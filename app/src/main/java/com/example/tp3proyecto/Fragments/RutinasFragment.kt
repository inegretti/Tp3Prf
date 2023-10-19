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
        viewModel = ViewModelProvider(this).get(RutinasViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rutinas, container, false)
        d=v.findViewById(R.id.dias)
        tit=v.findViewById(R.id.rutTit)
        tit.text="Semana"

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE

        return v
    }

    override fun onStart() {
        super.onStart()
        usuario = viewModel.getInstance()

        adapterD= AdapterDia(usuario.semana){position->
            Snackbar.make(v,"posicion ${position}",Snackbar.LENGTH_LONG).show()
        }

        d.layoutManager=LinearLayoutManager(context)
        d.adapter=adapterD

    }
}