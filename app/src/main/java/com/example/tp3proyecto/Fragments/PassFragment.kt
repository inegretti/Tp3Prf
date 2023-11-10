package com.example.tp3proyecto.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.R
import com.google.android.material.snackbar.Snackbar

class PassFragment : Fragment() {

   // companion object {
   //     fun newInstance() = PassFragment()
   // }

    lateinit var v: View
    lateinit var contrasenia: EditText
    lateinit var confirmacion: EditText
    lateinit var btn: Button

    private lateinit var viewModel: PassViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_pass, container, false)
        contrasenia=v.findViewById(R.id.newPass)
        confirmacion=v.findViewById(R.id.ConfPass)
        btn=v.findViewById(R.id.btnPassCh)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PassViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        var z= PassFragmentArgs.fromBundle(requireArguments())
        btn.setOnClickListener(){
            if(contrasenia.text.isEmpty()){
                Snackbar.make(v, "Ingrese una contraseña", Snackbar.LENGTH_LONG).show()
            }else{
                if(contrasenia.text.toString()!="password"){
                    if(contrasenia.text.toString()==confirmacion.text.toString()){
                        //z.usuario.password=contrasenia.text.toString()
                        z.repo.lista.get(z.repo.lista.indexOf(z.usuario)).password=contrasenia.text.toString()
                        z.repo.database.collection("users").document(z.usuario.Email).update("password",z.usuario.password).addOnSuccessListener {  }
                        Snackbar.make(v, "la contraseña ha sido modificada", Snackbar.LENGTH_LONG).show()
                        findNavController().navigateUp()
                    }else{
                        Snackbar.make(v, "la contrasenia y confirmacion no coinciden", Snackbar.LENGTH_LONG).show()
                    }

                }else{
                    Snackbar.make(v, "por favor cambie la contrasenia", Snackbar.LENGTH_LONG).show()
                }

            }

        }
    }

}