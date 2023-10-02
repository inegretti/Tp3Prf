package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class AgregarUsuarioFragment : Fragment() {



    private lateinit var v: View
    private lateinit var nombre:EditText
    private lateinit var email:EditText
    private lateinit var contrasenia:EditText
    private lateinit var confirmacionContrasenia:EditText
    private lateinit var peso:EditText
    private lateinit var altura:EditText
    private lateinit var btnIng:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_agregar_usuario, container, false)
        nombre=v.findViewById(R.id.ingNom)
        email=v.findViewById(R.id.ingEmail)
        contrasenia=v.findViewById(R.id.ingCon)
        confirmacionContrasenia=v.findViewById(R.id.confCon)
        peso=v.findViewById(R.id.ingPesKg)
        altura=v.findViewById(R.id.ingAlt)
        btnIng=v.findViewById(R.id.btnConIng)
        return v
    }

    override fun onStart() {
        super.onStart()
        var z = AdminFragmentArgs.fromBundle(requireArguments())
        btnIng.setOnClickListener(){


            if(nombre.text.isEmpty() || contrasenia.text.isEmpty() || contrasenia.text.isEmpty() || altura.text.isEmpty() || peso.text.isEmpty()){
                Snackbar.make(v,"No puede haber campos vacios", Snackbar.LENGTH_LONG).show()
            }else{
            if(peso.text.toString().toDouble()<=10 || altura.text.toString().toDouble()<=1 ){
                Snackbar.make(v,"la altura no puede ser menor a 1 mt y el peso no pude ser menor a 10", Snackbar.LENGTH_LONG).show()
                }else{
                if(z.usuarios.lista.find {it.Email==email.text.toString()} == null){
                    if(contrasenia.text.toString() == confirmacionContrasenia.text.toString()){
                        z.usuarios.lista.add(Usuario((z.usuarios.lista.size-1),nombre.text.toString(),contrasenia.text.toString(),email.text.toString(),"",peso.text.toString().toDouble(),altura.text.toString().toDouble()))
                        Snackbar.make(v,"Usuario Registrados", Snackbar.LENGTH_LONG).show()
                        findNavController().navigateUp()


                    }else{
                        Snackbar.make(v,"la contrasenia no coincide",
                            Snackbar.LENGTH_LONG).show()
                    }

                }else{
                    Snackbar.make(v,"ya existe un usuario registrado con ese mail",
                        Snackbar.LENGTH_LONG).show()
                }
                }


            }

        }

    }



}