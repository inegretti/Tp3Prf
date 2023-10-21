package com.example.tp3proyecto.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.example.tp3proyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var email: EditText
    lateinit var contraseña: EditText
    lateinit var btn: Button
    private lateinit var viewModel: LoginViewModel

    private lateinit var usuario: Usuario
    private lateinit var usuarioSingleton : UsuarioSingleton

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        //                            si aca pongo this solo aplicaria a este fragment
        //                            con activity aplica a toda la actividad(lo vuelvo un singleton)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        email = v.findViewById(R.id.email)
        contraseña = v.findViewById(R.id.contraseña)
        btn = v.findViewById(R.id.button2)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.GONE

        return v
    }
    override fun onStart() {
        super.onStart()
        btn.setOnClickListener() {
            var usuario = viewModel.validarUs(email.text.toString(),contraseña.text.toString())
            var e:String= email.text.toString()
            //var usuario=lista2.lista.find {it.Email==e}
           //Snackbar.make(v, "usuario ${usuario}", Snackbar.LENGTH_LONG).show()
            if(viewModel.usuario!=null){

                if(viewModel.validarPass(contraseña.text.toString())){

                    UsuarioSingleton.setUsuario(viewModel.usuario)

                    if(viewModel.usuario.name=="Admin"){
                        val action = LoginFragmentDirections.actionLoginFragmentToAdminFragment(viewModel.lista2)
                        findNavController().navigate(action)
                    }else{
                        val action = LoginFragmentDirections.actionLoginFragmentToUserFragment(viewModel.usuario)
                        findNavController().navigate(action)
                    }
                }else{
                    Snackbar.make(v, "contraseña incorrecta", Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(v, "No existe un usuario registrado con ese mail", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
