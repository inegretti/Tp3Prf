package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Ejercicio
import com.example.tp3proyecto.R
import com.example.tp3proyecto.Repository.Repositorio
import com.google.android.material.snackbar.Snackbar

class AgregarEjercicioFragment : Fragment() {



    private lateinit var v: View
    private lateinit var spin:Spinner
    private lateinit var ingRep:TextView
    private lateinit var ingSer:TextView
    private lateinit var btn:Button
    var rep:Repositorio = Repositorio()
    //var z=AgregarEjercicioFragmentArgs.fromBundle(requireArguments())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_agregar_ejercicio, container, false)
        spin=v.findViewById(R.id.spinnerEj)
        ingRep=v.findViewById(R.id.ingRep)
        ingSer=v.findViewById(R.id.ingSer)
        btn = v.findViewById(R.id.btnAAR)


        var op:MutableList<String> = mutableListOf()
        for (ejercicio in rep.listaE) {
            op.add(ejercicio.nombre)
        }
        var  adapter:ArrayAdapter<String>
        adapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_spinner_item,op)
        spin.adapter=adapter
        return v
    }

    override fun onStart() {
        super.onStart()

        var z = AgregarEjercicioFragmentArgs.fromBundle(requireArguments())
        btn.setOnClickListener() {
            if (ingSer.text.isEmpty() || ingSer.text.isEmpty()) {
                Snackbar.make(
                    v,
                    "los datos no pueden ser vacios o menores a 0",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                var obj = rep.listaE.find { it.nombre == spin.selectedItem.toString() }
                if (obj != null) {
                    obj.series = Integer.parseInt(ingSer.text.toString())
                    obj.repeticiones = Integer.parseInt(ingRep.text.toString())
                    z.usuario.semana[z.posicion].rutina.add(obj)
                    Snackbar.make(v, "Se ha agregado el ejercicio", Snackbar.LENGTH_LONG).show()
                    findNavController().navigateUp()
                } else {
                    Snackbar.make(
                        v,
                        "no se ha encontrado un ejercicio con ese nombre",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }


        }
    }
    }



