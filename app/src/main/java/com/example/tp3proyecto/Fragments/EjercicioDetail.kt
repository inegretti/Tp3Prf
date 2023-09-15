package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.R

class EjercicioDetail : Fragment() {


    private lateinit var v: View
    private lateinit var t:TextView
    private lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_ejercicio_detail, container, false)
        t=v.findViewById(R.id.ejNom)
        btn= v.findViewById(R.id.btnBajaEj)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z=EjercicioDetailArgs.fromBundle(requireArguments())
        t.text="nombre ${z.ejercicio.nombre} series ${z.ejercicio.series} repeticiones${z.ejercicio.repeticiones}"
        if(z.clearence){
            btn.setVisibility(View.VISIBLE)

        }else{
            btn.setVisibility(View.INVISIBLE)
        }

        btn.setOnClickListener() {
                z.usuario.semana[z.posicion].rutina.remove(z.ejercicio)
            findNavController().navigateUp()


        }
    }

}