package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.R

class EjercicioDetail : Fragment() {


    private lateinit var v: View
    private lateinit var t:TextView
    private lateinit var btn: Button
    private lateinit var btnC: Button
    private lateinit var com:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_ejercicio_detail, container, false)
        t=v.findViewById(R.id.ejNom)
        btn= v.findViewById(R.id.btnBajaEj)
        com=v.findViewById(R.id.comentario)
        btnC=v.findViewById(R.id.btnCom)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z=EjercicioDetailArgs.fromBundle(requireArguments())
        t.text="nombre ${z.ejercicio.nombre} series ${z.ejercicio.series} repeticiones${z.ejercicio.repeticiones}"
        com.text= Editable.Factory.getInstance().newEditable(z.ejercicio.comentario)
        if(z.clearence){
            btn.setVisibility(View.VISIBLE)
            com.isEnabled=false
            btnC.setVisibility(View.INVISIBLE)


        }else{
            btn.setVisibility(View.INVISIBLE)
            if(z.ejercicio.estado){
                btnC.text="Actualizar comentario"
            }else{

            }
        }

        btn.setOnClickListener() {
                z.usuario.semana[z.posicion].rutina.remove(z.ejercicio)
            findNavController().navigateUp()


        }

        btnC.setOnClickListener(){
            z.ejercicio.estado=true
            z.ejercicio.comentario=com.text.toString()
            findNavController().navigateUp()
        }
    }

}