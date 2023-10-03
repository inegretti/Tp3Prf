package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.R

class EjercicioDetail : Fragment() {


    private lateinit var v: View
    private lateinit var t:TextView
    private lateinit var btn: Button
    private lateinit var btnC: Button
    private lateinit var com:EditText
    private lateinit var desc:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_ejercicio_detail, container, false)
        t=v.findViewById(R.id.ejNom)
        btn= v.findViewById(R.id.btnBajaEj)
        desc=v.findViewById(R.id.descripcion)
        com=v.findViewById(R.id.comentario)
        btnC=v.findViewById(R.id.btnCom)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z=EjercicioDetailArgs.fromBundle(requireArguments())
        t.text="Nombre ${z.ejercicio.nombre} Series ${z.ejercicio.series} Repeticiones${z.ejercicio.repeticiones}"
        desc.text= Editable.Factory.getInstance().newEditable(z.ejercicio.descripcion)
        com.text= Editable.Factory.getInstance().newEditable(z.ejercicio.comentario)
        if(z.clearence){
            btn.setVisibility(View.VISIBLE)
            com.isEnabled=false
            btnC.setVisibility(View.INVISIBLE)


        }else{
            btn.setVisibility(View.INVISIBLE)
            desc.isEnabled=false
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
            z.ejercicio.descripcion=desc.text.toString()
            z.ejercicio.comentario=com.text.toString()
            findNavController().navigateUp()
        }
    }

}