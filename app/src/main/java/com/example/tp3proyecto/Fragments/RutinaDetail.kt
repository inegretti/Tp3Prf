package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tp3proyecto.R

class RutinaDetail : Fragment() {



    private lateinit var v: View
    private lateinit var t:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=  inflater.inflate(R.layout.fragment_rutina_detail, container, false)
        t=v.findViewById(R.id.tituloRutinaDetail)
        return v
    }

    override fun onStart() {
        super.onStart()
        var z=RutinaDetailArgs.fromBundle(requireArguments())
        t.text=z.usuario.name

    }

}