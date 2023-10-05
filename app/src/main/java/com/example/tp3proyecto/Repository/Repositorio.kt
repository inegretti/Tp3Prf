package com.example.tp3proyecto.Repository

import android.os.Parcelable
import com.example.tp3proyecto.Entidades.Ejercicio
import com.example.tp3proyecto.Entidades.RegistroPeso
import com.example.tp3proyecto.Entidades.Usuario
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.*

@Parcelize
class Repositorio():Parcelable{

    var lista:MutableList<Usuario> = mutableListOf()
    var listaE:MutableList<Ejercicio> = mutableListOf()

    companion object {
        var listaA:MutableList<Usuario> = mutableListOf()
        var listaC:MutableList<Ejercicio> = mutableListOf()
    }

     init{
         var admin:Usuario
         admin=Usuario(1,"Admin","12345","admin@gmail.com","41444444",0.0,1.8)
        // lista.add(Usuario(1,"Admin","12345","admin@gmail.com","41444444",0.0,1.8)) admin.esAdmin=true
         listaA.add(admin)
         listaA.add(Usuario(2,"Juan","Juan2","juan@gmail.com","41444444",68.0,1.90))
         listaA.add(Usuario(3,"pedro","76952","pet@gmail.com","41444444",67.0,1.75))
         listaA.add(Usuario(4,"alejo","alejo","ale88@gmail.com","41444444",80.0,1.6))
         listaA.add(Usuario(5,"martin","tincho22","martin@gmail.com","41444444",45.0,1.7))
         listaA.add(Usuario(6,"Brian","br14n","martin@gmail.com","41444444",90.0,1.86))
         listaC.add(Ejercicio(1,"FLEXIONES DE BRAZOS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaC.add(Ejercicio(2,"SENTADILLAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaC.add(Ejercicio(3,"PLANCHAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaC.add(Ejercicio(4,"ZANCADAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaC.add(Ejercicio(5,"DOMINADAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaC.add(Ejercicio(6,"BANDERA HUMANA","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaA[1].semana[0].rutina.add(listaC[0])
         listaA[1].semana[0].rutina.add(Ejercicio(1,"flexiones de brazos","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaA.get(1).semana.get(0).rutina.add(listaC.get(3))
         listaA.get(1).semana.get(0).rutina.add(listaC.get(4))

        //-------------------------------------------------------------------------------------------

         lista.add(admin)
         lista.add(Usuario(2,"Juan","Juan2","juan@gmail.com","41444444",68.0,1.90))
         lista.add(Usuario(3,"pedro","76952","pet@gmail.com","41444444",67.0,1.75))
         lista.add(Usuario(4,"alejo","alejo","ale88@gmail.com","41444444",80.0,1.6))
         lista.add(Usuario(5,"martin","tincho22","martin@gmail.com","41444444",45.0,1.7))
         lista.add(Usuario(6,"Brian","br14n","martin@gmail.com","41444444",90.0,1.86))
         listaE.add(Ejercicio(1,"FLEXIONES DE BRAZOS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaE.add(Ejercicio(2,"SENTADILLAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaE.add(Ejercicio(3,"PLANCHAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaE.add(Ejercicio(4,"ZANCADAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaE.add(Ejercicio(5,"DOMINADAS","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         listaE.add(Ejercicio(6,"BANDERA HUMANA","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         
         lista[1].semana[0].rutina.add(listaE[0])
         lista[1].semana[0].rutina.add(Ejercicio(1,"flexiones de brazos","https://i.blogs.es/886311/flexiones/1366_2000.webp"))
         lista.get(1).semana.get(0).rutina.add(listaE.get(3))
         lista.get(1).semana.get(0).rutina.add(listaE.get(4))

     }




    fun agregar(usuario:Usuario){
        lista.add(usuario)
    }


}