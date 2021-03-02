package com.e.mislugares.casos_uso

import android.app.Activity
import android.content.Intent
import com.e.mislugares.presentacion.AcercaDeActivity
import com.e.mislugares.presentacion.MapaActivity
import com.e.mislugares.presentacion.PreferenciasActivity

class CasosUsoActividades(val actividad: Activity) {

    fun lanzarAcerdaDe() =   actividad.startActivity(
        Intent(actividad, AcercaDeActivity::class.java))

    fun lanzarPreferencias(codidoSolicitud: Int)
            = actividad.startActivityForResult(
        Intent(actividad, PreferenciasActivity::class.java), codidoSolicitud)

    fun lanzarMapa() =   actividad.startActivity(
        Intent(actividad, MapaActivity::class.java))

}