package com.example.tp3proyecto.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3proyecto.Adapters.AdapterUsuario
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.example.tp3proyecto.Repository.Repositorio
import com.google.android.material.snackbar.Snackbar

class AdminFragment : Fragment() {

    lateinit var v:View
    lateinit var r:RecyclerView
    lateinit var adapter:AdapterUsuario
    lateinit var btn:Button
    lateinit var btnIng:Button
    lateinit var btnIANE:Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_admin, container, false)
        r=v.findViewById(R.id.adminView)
        btn=v.findViewById(R.id.btnLgAd)
        btnIng=v.findViewById(R.id.btnIngUs)
        btnIANE=v.findViewById(R.id.btnIANE)

        return v
    }

    override fun onStart() {
        super.onStart()
        var z = AdminFragmentArgs.fromBundle(requireArguments())
        //abro funcion con {} como metodo
        adapter= AdapterUsuario(z.usuarios.lista){
                // es lo que envia el adapter
                position->
            if(z.usuarios.lista.get(position).name=="Admin"){
                Snackbar.make(v,"No se puede acceder al usuario admin, contactarse con servicio tecnico",Snackbar.LENGTH_LONG).show()
            }else{
                var t:Usuario=z.usuarios.lista.get(position)
                var r: Repositorio=z.usuarios
                val action = AdminFragmentDirections.actionAdminFragmentToUserAdmFragment(t,r)
                findNavController().navigate(action)
            }



        }
        //configurar
        r.layoutManager=LinearLayoutManager(context)
        r.adapter=adapter

        btn.setOnClickListener(){
            /*
            var usuario = Usuario(
                id = 0,
                name = "Nombre",
                password = "Contraseña",
                Email = "email@example.com",
                imgUrl = "URL de la imagen",
                pesoActual = 0.0,
                altura = 0.0
            )
            UsuarioSingleton.setUsuario(usuario)
            */
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cerrar sesión")
            builder.setMessage("¿Está seguro que desea cerrar sesión?")

            builder.setPositiveButton("Sí") { dialog, which ->

                var usuario = Usuario(
                    id = 0,
                    name = "Nombre",
                    password = "Contraseña",
                    Email = "email@example.com",
                    imgUrl = "URL de la imagen",
                    pesoActual = 0.0,
                    altura = 0.0
                )


                 UsuarioSingleton.setUsuario(usuario)



                //val action = DatosUsuarioFragmentDirections.actionDatosUsuarioFragmentToLoginFragment(usuario)
                //findNavController().navigate(action)
                findNavController().navigateUp()
                //Snackbar.make(v,"Adios Admin",Snackbar.LENGTH_LONG).show()
                Snackbar.make(v,"usuario actual ${UsuarioSingleton.getInstance().name}",Snackbar.LENGTH_LONG).show()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        btnIng.setOnClickListener(){
            val action = AdminFragmentDirections.actionAdminFragmentToAgregarUsuarioFragment(z.usuarios)
            findNavController().navigate(action)
            Snackbar.make(v,"funciona el boton ",Snackbar.LENGTH_LONG).show()
        }


        btnIANE.setOnClickListener(){
            val action = AdminFragmentDirections.actionAdminFragmentToAgregarEjNewFragment(z.usuarios)
            findNavController().navigate(action)
            Snackbar.make(v,"funciona el boton ",Snackbar.LENGTH_LONG).show()
        }

    }



}