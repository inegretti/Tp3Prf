package com.example.tp3proyecto.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DatosUsuarioFragment : Fragment() {

    lateinit var v: View
    lateinit var nombreUsuario : TextView
    lateinit var mailUsuario : TextView
    lateinit var pesoUsuario : TextView
    lateinit var alturaUsuario : TextView
    lateinit var imagenUsuario : ImageView
    lateinit var cerrarSesionBoton : Button

    private lateinit var viewModel: DatosUsuarioViewModel
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatosUsuarioViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_datos_usuario, container, false)
        nombreUsuario = v.findViewById(R.id.nombreUsuario)
        mailUsuario  = v.findViewById(R.id.mailUsuario)
        pesoUsuario  = v.findViewById(R.id.pesoUsuario)
        alturaUsuario = v.findViewById(R.id.alturaUsuario)
        imagenUsuario = v.findViewById(R.id.imagenUsuario)
        imagenUsuario.setImageResource(R.drawable.perfil)
        cerrarSesionBoton = v.findViewById(R.id.cerrarSesionBoton)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE

        return v
    }

    override fun onStart() {
        super.onStart()
        usuario = UsuarioSingleton.getInstance()
        nombreUsuario.text = "${usuario.name}"
        mailUsuario.text = "${usuario.Email}"
        pesoUsuario.text = "${usuario.pesoActual}"
        alturaUsuario.text = "${usuario.altura}"

        cerrarSesionBoton.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cerrar sesión")
            builder.setMessage("¿Está seguro que desea cerrar sesión?")

            builder.setPositiveButton("Sí") { dialog, which ->

                usuario = Usuario(
                    id = 0,
                    name = "Nombre",
                    password = "Contraseña",
                    Email = "email@example.com",
                    imgUrl = "URL de la imagen",
                    pesoActual = 0.0,
                    altura = 0.0
                )


               // UsuarioSingleton.setUsuario(usuario)


                UsuarioSingleton.reiniciarinstance()
                val action = DatosUsuarioFragmentDirections.actionDatosUsuarioFragmentToLoginFragment(usuario)
                findNavController().navigate(action)
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}