package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R
import com.example.tp3proyecto.Repository.Repositorio
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AgregarEjercicioFragment : Fragment() {



    private lateinit var v: View
    private lateinit var spin:Spinner
    private lateinit var ingRep:TextView
    private lateinit var ingSer:TextView
    private lateinit var ingDesc:TextView
    private lateinit var btn:Button
    //var rep:Repositorio = Repositorio
    var listaC= mutableListOf<Ejercicio>()
    val database = Firebase.firestore


    /*
    var z =


            database.collection("ejercicios").get()
                .addOnSuccessListener {
                    for (Ejercicio in it.toObjects<Ejercicio>()) {
                        // var ej=Ejercicio(Ejercicio.id.toInt(),Ejercicio.nombre.toString(),Ejercicio.media.toString())
                        // listaC.add(ej)
                        Log.d("ejercicio", Ejercicio.nombre)
                        listaC.add(Ejercicio)
                    }
                    var op:MutableList<String> = mutableListOf()
                    for (ejercicio in listaC) {
                        op.add(ejercicio.nombre)

                    }

                }
*/




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_agregar_ejercicio, container, false)
        spin=v.findViewById(R.id.spinnerEj)
        ingRep=v.findViewById(R.id.ingRep)
        ingSer=v.findViewById(R.id.ingSer)
        ingDesc=v.findViewById(R.id.ingDesc)
        btn = v.findViewById(R.id.btnAAR)

        return v
    }

    override fun onStart() {
        super.onStart()
        database.collection("ejercicios").get()
            .addOnSuccessListener {
                for (Ejercicio in it.toObjects<Ejercicio>()) {
                    listaC.add(Ejercicio)
                }
                var op:MutableList<String> = mutableListOf()
                for (ejercicio in listaC) {
                    op.add(ejercicio.nombre)

                }
                var  adapter:ArrayAdapter<String>
                adapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_spinner_item,op)
                spin.adapter=adapter

            }




        var z = AgregarEjercicioFragmentArgs.fromBundle(requireArguments())
        btn.setOnClickListener() {
            if (ingSer.text.isEmpty() || ingSer.text.isEmpty() || ingDesc.text.isEmpty()) {
                Snackbar.make(
                    v,
                    "Los datos no pueden ser vacios o menores a 0",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                var obj = listaC.find { it.nombre == spin.selectedItem.toString() }

                if (obj != null) {
                    var copia:Ejercicio
                    copia= Ejercicio(obj.id,obj.nombre,obj.media)
                    copia.series=Integer.parseInt(ingSer.text.toString())
                        copia.repeticiones=Integer.parseInt(ingRep.text.toString())
                    copia.descripcion=ingDesc.text.toString()
                    z.usuario.semana[z.posicion].rutina.add(copia)
                    database.collection("users").document(z.usuario.Email).update("semana",z.usuario.semana).addOnSuccessListener {  }
                    Snackbar.make(v, "Se ha agregado el ejercicio", Snackbar.LENGTH_LONG).show()
                    findNavController().navigateUp()
                } else {
                    Snackbar.make(
                        v,
                        "No se ha encontrado un ejercicio con ese nombre",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }


        }
    }
    }



