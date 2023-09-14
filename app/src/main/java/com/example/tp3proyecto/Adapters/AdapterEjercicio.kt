package com.example.tp3proyecto.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Entidades.Ejercicio
import com.example.tp3proyecto.R

class AdapterEjercicio(var lista:MutableList<Ejercicio>,var onClick:(Int)->Unit):RecyclerView.Adapter<AdapterEjercicio.EjercicioHolder>() {

    class EjercicioHolder(v: View) : RecyclerView.ViewHolder(v){
        var s:View=v

        fun setEjercicio(ejercicio:String){
            Log.d("llega",ejercicio)
            val txt: TextView = s.findViewById(R.id.ejercicioTxt)
            txt.text=ejercicio
        }

        fun getCard(): CardView {
            return s.findViewById(R.id.cardEjercicio)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.ejercicio_item,parent,false)
        return EjercicioHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: EjercicioHolder, position: Int) {

        holder.setEjercicio("${lista[position].nombre} cantidad de series: ${lista[position].series}")
        holder.getCard().setOnClickListener {
            //que envie la posicion
            onClick(position)
        }
    }
}