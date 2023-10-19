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

class AdapterEjercicio(var lista: MutableList<Ejercicio>, var onClick: (Int) -> Unit) : RecyclerView.Adapter<AdapterEjercicio.EjercicioHolder>() {

    class EjercicioHolder(v: View) : RecyclerView.ViewHolder(v) {
        var s: View = v

        fun setEjercicio(nombre: String, series: String, repeticiones: String,  estado: String) {
            val nombreTextView: TextView = s.findViewById(R.id.nombreEjercicio)
            val seriesTextView: TextView = s.findViewById(R.id.seriesEjercicio)
            val repeticionesTextView: TextView = s.findViewById(R.id.repeticionesEjercicio)
            val estadoTextView: TextView = s.findViewById(R.id.estadoEjercicio)

            nombreTextView.text = nombre
            seriesTextView.text = series
            repeticionesTextView.text = repeticiones
            estadoTextView.text = estado
        }

        fun getCard(): CardView {
            return s.findViewById(R.id.cardEjercicio)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ejercicio_item, parent, false)
        return EjercicioHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: EjercicioHolder, position: Int) {
        val ejercicio = lista[position]
        val nombre = ejercicio.nombre
        val series = "Series: ${ejercicio.series}     "
        val repeticiones = "Repeticiones: ${ejercicio.repeticiones}"
        val estado = if (ejercicio.estado) "Estado: Completo" else "Estado: Incompleto"

        holder.setEjercicio(nombre, series, repeticiones, estado)

        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }
}