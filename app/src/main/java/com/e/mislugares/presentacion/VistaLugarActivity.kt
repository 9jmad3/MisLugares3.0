package com.e.mislugares.presentacion

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.mislugares.R
import com.e.mislugares.casos_uso.CasosUsoLugar
import com.e.mislugares.modelo.Lugar
import java.text.DateFormat
import java.util.*
import kotlinx.android.synthetic.main.vista_lugar.*

class VistaLugarActivity : AppCompatActivity() {
//    private lateinit var uriUltimaFoto: Uri
//    val RESULTADO_GALERIA = 2
//    val RESULTADO_FOTO = 3
//    val lugares by lazy { (application as Aplicacion).lugares }
//    val usoLugar by lazy { CasosUsoLugar(activity!!,this, lugares, adaptador ) }
//    val adaptador by lazy { (application as Aplicacion).adaptador }
//    var pos = 0
//    lateinit var lugar: Lugar
//    val RESULTADO_EDITAR = 1
//    private var _id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_lugar)
    }
//        pos = intent.extras?.getInt("pos", 0) ?: 0
//
//        lugar = adaptador.lugarPosicion (pos)
//        _id = adaptador.idPosicion(pos)
//        actualizaVistas()
//    }    <----------------------------------------
//
//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int,
//                                  data: Intent?) {
//        if (requestCode == RESULTADO_EDITAR) {
//            actualizaVistas()
//            scrollView1.invalidate()
//        }else if (requestCode == RESULTADO_GALERIA) {
//            if (resultCode == RESULT_OK) {
//                usoLugar.ponerFoto(pos, data?.dataString ?: "", foto)
//            } else {
//                Toast.makeText(this, "Foto no cargada", Toast.LENGTH_LONG).show();
//            }
//        }else if (requestCode == RESULTADO_FOTO) {
//            if (resultCode == Activity.RESULT_OK) {
//                lugar.foto = uriUltimaFoto.toString()
//                usoLugar.ponerFoto(pos, lugar.foto, foto);
//            } else {
//                Toast.makeText(this, "Error en captura", Toast.LENGTH_LONG).show()
//            }
//        }
//
//    }
//
//    @SuppressLint("SetTextI18n")
//    fun actualizaVistas(){
//        nombre.text = lugar.nombre
//        logo_tipo.setImageResource(lugar.tipoLugar.recurso)
//        tipo.text = lugar.tipoLugar.texto
//        direccion.text = lugar.direccion
//        telefono.text = Integer.toString(lugar.telefono)
//        url.text = lugar.url
//        comentario.text = lugar.comentarios
//        fecha.text = DateFormat.getDateInstance().format(Date(lugar.fecha))
//        hora.text = DateFormat.getTimeInstance().format(Date(lugar.fecha))
//        valoracion.rating = lugar.valoracion
//        valoracion.setOnRatingBarChangeListener {
//            ratingBar, valor, fromUser -> lugar.valoracion = valor
//        }
//        usoLugar.visualizarFoto(lugar, foto);
//        valoracion.setOnRatingBarChangeListener { _, _, _ -> }
//        valoracion.setRating(lugar.valoracion)
//        valoracion.setOnRatingBarChangeListener { _, valor, _ ->
//            lugar.valoracion = valor
//            usoLugar.actualizaPosLugar(pos, lugar)
//            pos = adaptador.posicionId(_id)
//        }
//
//
//
//    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.vista_lugar, menu)
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.getItemId()) {
//            R.id.accion_compartir -> {
//                usoLugar.compartir(lugar)
//                return true
//            }
//            R.id.accion_llegar -> {
//
//                usoLugar.verMapa(lugar)
//                return true
//            }
//            R.id.accion_editar -> return true
//            R.id.accion_borrar -> {
//                val _id = adaptador.idPosicion(pos)
//                usoLugar.borrar(_id)
//                return true
//            }
//
//
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
//
//    fun verMapa(view: View) = usoLugar.verMapa(lugar)
//    fun llamarTelefono(view: View) = usoLugar.llamarTelefono(lugar)
//    fun verPgWeb(view: View) = usoLugar.verPgWeb(lugar)
//    fun ponerDeGaleria(view: View)= usoLugar.ponerDeGaleria(RESULTADO_GALERIA)
//    fun tomarFoto(view: View) { uriUltimaFoto = usoLugar.tomarFoto(RESULTADO_FOTO)!!
//    }
//    fun eliminarFoto(view: View) = usoLugar.ponerFoto(pos, "", foto)
//
//


}