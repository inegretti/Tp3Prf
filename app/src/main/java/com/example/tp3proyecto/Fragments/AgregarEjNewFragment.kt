package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Ejercicio
import com.example.tp3proyecto.R
import com.example.tp3proyecto.Repository.Repositorio
import com.google.android.material.snackbar.Snackbar

class AgregarEjNewFragment : Fragment() {



    private lateinit var v: View
    private lateinit var btn:Button
    private lateinit var nombre: EditText
    private lateinit var mult: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_agregar_ej_new, container, false)
        btn=v.findViewById(R.id.btnIngNewEj)
        nombre=v.findViewById(R.id.ingNewEjNom)
        mult=v.findViewById(R.id.ingNewEjMul)
        return v
    }

    override fun onStart() {
        super.onStart()

        btn.setOnClickListener(){
            var z = AgregarEjNewFragmentArgs.fromBundle(requireArguments())
            if(nombre.text.isEmpty()||mult.text.isEmpty()){
                Snackbar.make(v,"Complete todos los campos ", Snackbar.LENGTH_LONG).show()

            }else{
                if(z.listaEjercicio.listaE.find {it.nombre==nombre.text.toString().uppercase()} == null){

                    z.listaEjercicio.listaE.add(Ejercicio(z.listaEjercicio.listaE.size-1,nombre.text.toString().uppercase(),mult.text.toString()))
                    Repositorio.listaC.add(Ejercicio(Repositorio.listaC.size-1,nombre.text.toString().uppercase(),mult.text.toString()))
                    for (ejercicio in  z.listaEjercicio.listaE) {
                        Log.d("Ejercicios:",ejercicio.nombre)
                    }
                    findNavController().navigateUp()
                } else{
                    Snackbar.make(v,"Ya existe un ejercicio con ese nombre ", Snackbar.LENGTH_LONG).show()
                }

            }

        }
    }

}