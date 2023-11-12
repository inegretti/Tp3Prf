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
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Ejercicio
import com.example.tp3proyecto.R
import com.example.tp3proyecto.Repository.Repositorio
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarEjNewFragment : Fragment() {



    private lateinit var v: View
    private lateinit var btn:Button
    private lateinit var nombre: EditText
    private lateinit var mult: EditText
    val database = Firebase.firestore
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

        btn.setOnClickListener {
            val z = AgregarEjNewFragmentArgs.fromBundle(requireArguments())
            val nombreValue = nombre.text.toString().uppercase()
            val multValue = mult.text.toString()

            val viewModel = ViewModelProvider(this).get(AgregarEjNewViewModel::class.java)

            if (viewModel.validarCampos(nombreValue, multValue)) {
                if (!viewModel.ejercicioExistente(nombreValue, z.listaEjercicio.listaE)) {
                    val nuevoEjercicio = Ejercicio(z.listaEjercicio.listaE.size, nombreValue, multValue)
                    database.collection("ejercicios").document(nuevoEjercicio.nombre).set(nuevoEjercicio)
                    z.listaEjercicio.listaE.add(nuevoEjercicio)
                    Repositorio.listaC.add(nuevoEjercicio)
                    findNavController().navigateUp()
                } else {
                    Snackbar.make(v, "Ya existe un ejercicio con ese nombre", Snackbar.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(v, "Complete todos los campos", Snackbar.LENGTH_LONG).show()
            }
        }
    }


}