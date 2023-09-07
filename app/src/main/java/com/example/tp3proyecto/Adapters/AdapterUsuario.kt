package com.example.tp3proyecto.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R
                                                //variable metodo que devuelve vacio
class AdapterUsuario(var lista: MutableList<Usuario>,var onClick:(Int)->Unit):RecyclerView.Adapter<AdapterUsuario.UsuarioHolder>() {



    class UsuarioHolder(v: View) : RecyclerView.ViewHolder(v) {
    //interactua con el item
        //seteamos un intermediario (caso contrario no funciona)
        var s:View=v


        //seteamos el email y el password de los usuarios
        fun setDatos(datos:String){
            val txtEmail:TextView= s.findViewById(R.id.textUs)
            txtEmail.text=datos
        }

        fun setContrasenia(contrasenia:String){
            val txtContrasenia:TextView= s.findViewById(R.id.textPas)
            txtContrasenia.text=contrasenia

        }

        //devuelve la carta
        fun getCard():CardView{
            return s.findViewById(R.id.cardUsuario)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioHolder {
        //aca es hacer copy paste
        val view= LayoutInflater.from(parent.context).inflate(R.layout.usuario_item,parent,false)
        return UsuarioHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: UsuarioHolder, position: Int) {
        //interacion de los metodos
        holder.setDatos("Nombre:${lista[position].name} Email: ${lista[position].Email}")
        holder.setContrasenia(lista[position].password)
        //al presionarlo
        holder.getCard().setOnClickListener {
            //que envie la posicion
                onClick(position)
        }

    }
}