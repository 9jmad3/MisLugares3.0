package com.e.mislugares.presentacion
import android.app.Application
import com.e.mislugares.datos.AdaptadorLugaresBD
import com.e.mislugares.datos.LugaresBD
import com.e.mislugares.datos.LugaresLista
import com.e.mislugares.modelo.GeoPunto

class Aplicacion : Application() {
    val lugares = LugaresBD(this)
    val adaptador by lazy {
        AdaptadorLugaresBD(lugares, lugares.extraeCursor())

    }
    val posicionActual = GeoPunto.SIN_POSICION

    var saldo: Int = 0
    override fun onCreate() {
        super.onCreate()
        val pref = getSharedPreferences("pref", MODE_PRIVATE)
        saldo = pref.getInt("saldo_inicial", -1)
    }

}