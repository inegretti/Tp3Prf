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
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserAdmFragment : Fragment() {



    private lateinit var v: View
    private lateinit var nombreText:TextView
    private lateinit var emailText:TextView
    private lateinit var contraseniaText:TextView
    private lateinit var pesoAlturaText:TextView
    private lateinit var btn:Button
    private lateinit var btnR:Button
    private lateinit var btnRutina:Button
    val database = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_adm, container, false)
        nombreText = v.findViewById(R.id.nombreUsuario)
        emailText = v.findViewById(R.id.emailUsuario)
        contraseniaText = v.findViewById(R.id.contraseniaUsuario)
        pesoAlturaText = v.findViewById(R.id.textPesoAlturaUsuario)

        btn = v.findViewById(R.id.btnBaja)
        btnR = v.findViewById(R.id.btnResetPass)
        btnRutina = v.findViewById(R.id.btnRutina)
        return v
    }

    override fun onStart() {
        super.onStart()
        var z = UserAdmFragmentArgs.fromBundle(requireArguments())
        nombreText.text="${z.usuario.name}"
        pesoAlturaText.text = "Peso actual: ${z.usuario.pesoActual}kg    Altura: ${z.usuario.altura}mts"
        contraseniaText.text = "Contraseña: ${z.usuario.password}"
        emailText.text = "Email: ${z.usuario.Email}"

        btn.setOnClickListener(){
            z.lista.lista.remove(z.usuario)
            database.collection("users").document(z.usuario.Email).delete()
            Snackbar.make(v,"El usuario ha sido removido", Snackbar.LENGTH_LONG).show()
            findNavController().navigateUp()

        }

        btnR.setOnClickListener(){
            var pos=z.lista.lista.indexOf(z.usuario)

            z.lista.lista.get(pos).password="password"
            database.collection("users").document(z.usuario.Email).update("password","password")
            findNavController().navigateUp()
            Snackbar.make(v,"La contraseña fue cambiada a password", Snackbar.LENGTH_LONG).show()
        }


            btnRutina.setOnClickListener() {
                //Snackbar.make(v, "proximamente", Snackbar.LENGTH_LONG).show()
                val action =  UserAdmFragmentDirections.actionUserAdmFragmentToRutinasFragment(z.usuario,true)
                UsuarioSingleton.setUsuario(z.usuario)
                findNavController().navigate(action)

            }

    }
}