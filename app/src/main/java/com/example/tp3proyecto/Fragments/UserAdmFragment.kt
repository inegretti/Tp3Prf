package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class UserAdmFragment : Fragment() {



    private lateinit var v: View
    private lateinit var t:TextView
    private lateinit var btn:Button
    private lateinit var btnR:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_user_adm, container, false)
        t=v.findViewById(R.id.usDatos)
        btn=v.findViewById(R.id.btnBaja)
        btnR=v.findViewById(R.id.btnResetPass)
        return v
    }

    override fun onStart() {
        super.onStart()
        var z = UserAdmFragmentArgs.fromBundle(requireArguments())
        t.text="Nombre: ${z.usuario.name} Altura: ${z.usuario.altura}mts Peso actual: ${z.usuario.pesoActual}kg contraseña: ${z.usuario.password} email: ${z.usuario.Email}"

        btn.setOnClickListener(){
            z.lista.lista.remove(z.usuario)
            Snackbar.make(v,"El usuario ha sido removido", Snackbar.LENGTH_LONG).show()
            findNavController().navigateUp()

        }

        btnR.setOnClickListener(){
            var pos=z.lista.lista.indexOf(z.usuario)

            z.lista.lista.get(pos).password="password"
            findNavController().navigateUp()
            Snackbar.make(v,"La contraseñan fue cambiada a password", Snackbar.LENGTH_LONG).show()
        }
    }
}