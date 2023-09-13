package com.example.tp3proyecto.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Entidades.Dia
import com.example.tp3proyecto.R

class AdapterDia(var lista:MutableList<Dia>,var onClick:(Int)->Unit):RecyclerView.Adapter<AdapterDia.DiaHolder>() {

    class DiaHolder(v: View):RecyclerView.ViewHolder(v){
        private var view:View
        init{
            this.view=v
        }
        fun setName(e:String){

            val txtName: TextView =view.findViewById(R.id.nomDia)
            txtName.text=e
         }


        fun getCard():CardView{
            return view.findViewById(R.id.cardDia)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.dia_item,parent,false)
        return (DiaHolder(view))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: DiaHolder, position: Int) {

        holder.setName(lista.get(position).nombre)
        holder.getCard().setOnClickListener{
            onClick(position)
        }
    }
}