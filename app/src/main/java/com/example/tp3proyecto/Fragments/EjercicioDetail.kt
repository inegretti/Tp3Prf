package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tp3proyecto.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EjercicioDetail : Fragment() {


    private lateinit var v: View
    private lateinit var ejercicioNombre:TextView
    private lateinit var ejercicioSeries:TextView
    private lateinit var ejercicioRepeticiones:TextView
    private lateinit var btn: Button
    private lateinit var btnC: Button
    private lateinit var com:EditText
    private lateinit var imagenDetails : ImageView
    private lateinit var desc:EditText
    val database = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_ejercicio_detail, container, false)
        ejercicioNombre= v.findViewById(R.id.ejercicioNombre)
        ejercicioSeries= v.findViewById(R.id.ejercicioSeries)
        ejercicioRepeticiones= v.findViewById(R.id.ejercicioRepeticiones)
        btn= v.findViewById(R.id.btnBajaEj)
        desc=v.findViewById(R.id.descripcion)
        com=v.findViewById(R.id.comentario)
        btnC=v.findViewById(R.id.btnCom)
        imagenDetails=v.findViewById(R.id.imgDetail)


        return v
    }

    override fun onStart() {
        super.onStart()
        var z=EjercicioDetailArgs.fromBundle(requireArguments())
        ejercicioNombre.text = Html.fromHtml("<u>Nombre:</u> ${z.ejercicio.nombre}")
        ejercicioSeries.text = Html.fromHtml("<u>Series:</u> ${z.ejercicio.series}")
        ejercicioRepeticiones.text = Html.fromHtml("<u>Repeticiones:</u> ${z.ejercicio.repeticiones}")


        val imgUrl = z.ejercicio.media
                val width = imagenDetails.width
                val height = imagenDetails.height

                Glide.with(this)
                    .load(imgUrl)
                    .apply(RequestOptions()
                        .override(width, height)
                        .centerInside() // Escala la imagen para que quepa dentro del ImageView
                    )
                    .into(imagenDetails)



        desc.text= Editable.Factory.getInstance().newEditable(z.ejercicio.descripcion)
        com.text= Editable.Factory.getInstance().newEditable(z.ejercicio.comentario)
        if (z.clearence){
            btn.setVisibility(View.VISIBLE)
            com.isEnabled=false
            btnC.setVisibility(View.INVISIBLE)


        } else {
            btn.setVisibility(View.INVISIBLE)
            desc.isEnabled=false
            if(z.ejercicio.estado){
                btnC.text="Actualizar comentario"
            }else{

            }
        }

        btn.setOnClickListener() {
                z.usuario.semana[z.posicion].rutina.remove(z.ejercicio)
            database.collection("users").document(z.usuario.Email).update("semana",z.usuario.semana).addOnSuccessListener {  }
                findNavController().navigateUp()
        }

        btnC.setOnClickListener(){
            z.ejercicio.estado=true
            z.ejercicio.descripcion=desc.text.toString()
            z.ejercicio.comentario=com.text.toString()
            database.collection("users").document(z.usuario.Email).update("semana",z.usuario.semana).addOnSuccessListener {  }
            findNavController().navigateUp()
        }
    }

}