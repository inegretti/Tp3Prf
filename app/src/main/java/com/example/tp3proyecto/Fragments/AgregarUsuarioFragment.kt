package com.example.tp3proyecto.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarUsuarioFragment : Fragment() {


    private lateinit var v: View
    private lateinit var nombre:EditText
    private lateinit var email:EditText
    private lateinit var contrasenia:EditText
    private lateinit var confirmacionContrasenia:EditText
    private lateinit var peso:EditText
    private lateinit var altura:EditText
    private lateinit var btnIng:Button
    val database = Firebase.firestore
    private lateinit var viewModel: AgregarUsuarioViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AgregarUsuarioViewModel::class.java)

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
        val z = AdminFragmentArgs.fromBundle(requireArguments())

        btnIng.setOnClickListener {
            val nombreValue = nombre.text.toString()
            val contraseniaValue = contrasenia.text.toString()
            val confirmacionValue = confirmacionContrasenia.text.toString()
            val alturaValue = altura.text.toString()
            val pesoValue = peso.text.toString()
            val emailValue = email.text.toString()

            if (viewModel.validarCampos(nombreValue, contraseniaValue, confirmacionValue, alturaValue.toString(), pesoValue.toString(), emailValue.toString())) {
                if (viewModel.validarAlturaPeso(alturaValue.toDouble(), pesoValue.toDouble())) {
                    if (!viewModel.usuarioExistente(emailValue, z.usuarios.lista)) {
                        if (viewModel.contraseniasCoinciden(contraseniaValue, confirmacionValue)) {
                            if (viewModel.correoVal(emailValue)) {
                                if (viewModel.passVal(contraseniaValue)) {
                                    val nuevoUsuario = Usuario(
                                        z.usuarios.lista.size - 1,
                                        nombreValue,
                                        contraseniaValue,
                                        emailValue,
                                        "",
                                        pesoValue.toDouble(),
                                        alturaValue.toDouble()
                                    )

                                    z.usuarios.lista.add(nuevoUsuario)
                                    database.collection("users").document(nuevoUsuario.Email).set(nuevoUsuario)
                                    Snackbar.make(v, "Usuario registrado", Snackbar.LENGTH_LONG).show()
                                    findNavController().navigateUp()
                                } else {
                                    Snackbar.make(v, "La contraseña debe contener al menos 8 caracteres, incluyendo mayúsculas, minúsculas y números.", Snackbar.LENGTH_LONG).show()
                                }
                            } else {
                                Snackbar.make(v, "Correo electrónico no válido", Snackbar.LENGTH_LONG).show()
                            }
                        } else {
                            Snackbar.make(v, "La contraseña no coincide", Snackbar.LENGTH_LONG).show()
                        }
                    } else {
                        Snackbar.make(v, "Ya existe un usuario registrado con ese correo electrónico", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Snackbar.make(v, "La altura no puede ser menor a 1 metro y el peso no puede ser menor a 10", Snackbar.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(v, "No puede haber campos vacíos", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}