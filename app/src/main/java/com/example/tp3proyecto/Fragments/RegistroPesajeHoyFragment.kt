package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.RegistroPeso
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Double
import java.time.LocalDate
import java.util.Date

class RegistroPesajeHoyFragment : Fragment() {



    private lateinit var v: View
    private lateinit var btn:Button
    private lateinit var text:EditText
    val database = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_registro_pesaje_hoy, container, false)
        btn=v.findViewById(R.id.btnRegPes)
        text=v.findViewById(R.id.ingPes)
        return v
    }

    override fun onStart() {
        super.onStart()
        var z = RegistroPesajeHoyFragmentArgs.fromBundle(requireArguments())
        btn.setOnClickListener(){
            if(text.text.isEmpty()){
                Snackbar.make(v, "Debe de registrar un numero", Snackbar.LENGTH_LONG).show()
            }else{
                var t:kotlin.Double
                t=text.text.toString().toDouble()
                if(t >0.0){
                    var fecha=LocalDate.now()
                    z.recep.pesoActual=t
                    var pos:Int
                    pos=0
                    var dia:Date
                    dia=Date(fecha.year,fecha.month.value,fecha.dayOfMonth)
                    for(Registro in z.recep.historialPeso){
                       if(Registro.fecha.before(dia)){
                           pos=z.recep.historialPeso.indexOf(Registro)
                       }else{

                       }

                    }
                    z.recep.historialPeso.add(pos,RegistroPeso(t,dia))
                    database.collection("users").document(z.recep.Email).update("pesoActual",z.recep.pesoActual)
                    database.collection("users").document(z.recep.Email).update("historialPeso",z.recep.historialPeso)
                    Snackbar.make(v, "peso actual ${z.recep.pesoActual}", Snackbar.LENGTH_LONG).show()
                    findNavController().navigateUp()


                }else{
                    Snackbar.make(v, "No puede ser menor a cero", Snackbar.LENGTH_LONG).show()
                }

            }

        }
    }

}