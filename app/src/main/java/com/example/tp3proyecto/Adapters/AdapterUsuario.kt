package com.example.tp3proyecto.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R

class AdapterUsuario(var lista: MutableList<Usuario>, var onClick: (Int) -> Unit) :
    RecyclerView.Adapter<AdapterUsuario.UsuarioHolder>() {

    class UsuarioHolder(v: View) : RecyclerView.ViewHolder(v) {
        var s: View = v

        // Establece el nombre del usuario en negrita
        fun setNombreUsuario(nombreUsuario: String) {
            val txtNombreUsuario: TextView = s.findViewById(R.id.textNombreUsuario)
            txtNombreUsuario.text = nombreUsuario
        }

        // Establece el email del usuario
        fun setEmailUsuario(email: String) {
            val txtEmailUsuario: TextView = s.findViewById(R.id.textEmailUsuario)
            txtEmailUsuario.text = "Email: $email"
        }

        // Establece la contraseña del usuario
        fun setContraseniaUsuario(contrasenia: String) {
            val txtContraseniaUsuario: TextView = s.findViewById(R.id.textContraseniaUsuario)
            txtContraseniaUsuario.text = "Contraseña: $contrasenia"
        }

        // Establece el peso y la altura del usuario en la misma línea
        fun setPesoAlturaUsuario(peso: Double, altura: Double) {
            val txtPesoAlturaUsuario: TextView = s.findViewById(R.id.textPesoUsuario)
            txtPesoAlturaUsuario.text = "Peso: $peso kg Altura: $altura mts"
        }

        fun getCard(): CardView {
            return s.findViewById(R.id.cardUsuario)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.usuario_item, parent, false)
        return UsuarioHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: UsuarioHolder, position: Int) {
        holder.setNombreUsuario(lista[position].name)
        holder.setEmailUsuario(lista[position].Email)
        holder.setContraseniaUsuario(lista[position].password)
        holder.setPesoAlturaUsuario(lista[position].pesoActual, lista[position].altura)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }
}
