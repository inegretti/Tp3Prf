package com.example.tp3proyecto.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
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
        nombreText.text=Html.fromHtml("<u>${z.usuario.name}</u>")
        pesoAlturaText.text = "Peso actual: ${z.usuario.pesoActual}kg    Altura: ${z.usuario.altura}mts"
        contraseniaText.text = "Contraseña: ${z.usuario.password}"
        emailText.text = "Email: ${z.usuario.Email}"

        btn.setOnClickListener {
            mostrarDialogoConfirmacion()
        }


        btnR.setOnClickListener(){
            var pos=z.lista.lista.indexOf(z.usuario)

            z.lista.lista.get(pos).password="password"
            database.collection("users").document(z.usuario.Email).update("password","password")
            findNavController().navigateUp()
            Snackbar.make(v,"La contraseña fue cambiada a password", Snackbar.LENGTH_LONG).show()
        }


            btnRutina.setOnClickListener() {
                val action =  UserAdmFragmentDirections.actionUserAdmFragmentToRutinasFragment(z.usuario,true)
                UsuarioSingleton.setUsuario(z.usuario)
                findNavController().navigate(action)

            }

    }

    private fun mostrarDialogoConfirmacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Dar de baja")
        builder.setMessage("¿Está seguro que desea dar de baja a este usuario?")

        builder.setPositiveButton("Sí") { dialog, which ->
            darDeBajaUsuario()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun darDeBajaUsuario() {
        val z = UserAdmFragmentArgs.fromBundle(requireArguments())
        z.lista.lista.remove(z.usuario)
        database.collection("users").document(z.usuario.Email).delete()
        Snackbar.make(v, "El usuario ha sido dado de baja", Snackbar.LENGTH_LONG).show()
        findNavController().navigateUp()
    }


}