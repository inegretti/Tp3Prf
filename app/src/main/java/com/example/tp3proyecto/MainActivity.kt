package com.example.tp3proyecto

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.tp3proyecto.Entidades.Usuario
import com.example.tp3proyecto.Entidades.UsuarioSingleton
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)

        bottomNavigationView.setupWithNavController(navController)

//        bottomNavigationView.setOnItemSelectedListener { item ->
//            when(item.itemId){
//                R.id.nav_logout -> {
//                    val builder = AlertDialog.Builder(this)
//                    builder.setTitle("Cerrar sesión?")
//                    builder.setPositiveButton("Sí", DialogInterface.OnClickListener { dialog, which ->
//                        usuario = usuario ?: null
//                        navController.navigate(R.id.loginFragment)
//                    })
//                    builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
//
//                    })
//                    builder.show()
//                    true
//                }
//                else -> false
//            }
//        }
    }
    override fun onStart() {
        super.onStart()
    }

}