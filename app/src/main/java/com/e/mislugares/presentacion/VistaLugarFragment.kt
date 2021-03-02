package com.e.mislugares.presentacion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.mislugares.R
import com.e.mislugares.presentacion.AdaptadorLugaresBD
import com.e.mislugares.casos_uso.CasosUsoLugarFecha
import com.e.mislugares.modelo.Lugar
import kotlinx.android.synthetic.main.vista_lugar.*
import java.text.DateFormat
import java.util.*

class VistaLugarFragment : Fragment() {
    private var uriUltimaFoto: Uri? =null

    val RESULTADO_GALERIA = 2
    val RESULTADO_FOTO = 3
    val lugares by lazy { (activity!!.application as Aplicacion).lugares }
    val usoLugar by lazy { CasosUsoLugarFecha(activity!!,this, lugares, adaptador ) }

    val adaptador by lazy { (activity!!.application as Aplicacion).adaptador }
    var pos = 0
    private lateinit var lugar: Lugar
    val RESULTADO_EDITAR = 1
    var _id: Int = -1

    override fun onCreateView(inflador: LayoutInflater, contenedor: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        setHasOptionsMenu(true)
        return inflador.inflate(R.layout.vista_lugar, contenedor, false)
    }
    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)
        pos = activity?.intent?.extras?.getInt("pos", 0) ?: 0
        _id=adaptador.idPosicion(pos)
        barra_url.setOnClickListener { usoLugar.verPgWeb(lugar) }
        barra_mapa.setOnClickListener { usoLugar.verMapa(lugar) }
        barra_telefono.setOnClickListener {usoLugar.llamarTelefono(lugar) }
        camara.setOnClickListener { usoLugar.tomarFoto(RESULTADO_FOTO) }
        galeria.setOnClickListener { usoLugar.ponerDeGaleria(RESULTADO_GALERIA) }
        eliminar_foto.setOnClickListener { usoLugar.ponerFoto(pos, "", foto) }
        icono_hora.setOnClickListener { usoLugar.cambiarHora(pos) }
        hora.setOnClickListener { usoLugar.cambiarHora(pos) }
        icono_fecha.setOnClickListener { usoLugar.cambiarFecha(pos) }
        fecha.setOnClickListener { usoLugar.cambiarFecha(pos) }

        lugar = adaptador.lugarPosicion(pos)
        actualizaVistas()
    }



    fun actualizaVistas(){
        if (adaptador.itemCount == 0) return
        lugar = adaptador.lugarPosicion(pos)
        nombre.setText(lugar.nombre)
        logo_tipo.setImageResource(lugar.tipoLugar.recurso)
        tipo.setText(lugar.tipoLugar.texto)
        direccion.setText(lugar.direccion)
        if (lugar.telefono == 0) {
            telefono.setVisibility(View.GONE)
        } else {
            telefono.setVisibility(View.VISIBLE)
            telefono.setText(Integer.toString(lugar.telefono))
        }
        url.setText(lugar.url)
        comentario.text =lugar.comentarios
        fecha.text= DateFormat.getDateInstance().format(Date(lugar.fecha))
        hora.text= DateFormat.getTimeInstance().format(Date(lugar.fecha))
        valoracion.setOnRatingBarChangeListener { _, _, _ -> }
        valoracion.setRating(lugar.valoracion)
        valoracion.setOnRatingBarChangeListener { _, valor, _ ->
            lugar.valoracion = valor
            usoLugar.actualizaPosLugar(pos, lugar)
            pos = adaptador.posicionId(_id)


        }
        usoLugar.visualizarFoto(lugar, foto)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.vista_lugar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.accion_compartir -> {
                usoLugar.compartir(lugar)
                return true
            }
            R.id.accion_llegar -> {
                usoLugar.verMapa(lugar)
                return true
            }
            R.id.accion_editar -> {
                usoLugar.editar(pos, RESULTADO_EDITAR)
                return true
            }
            R.id.accion_borrar -> {
                val _id = adaptador.idPosicion(pos)
                usoLugar.borrar(_id)
                return true
            }


            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun verMapa(view: View) = usoLugar.verMapa(lugar)
    fun llamarTelefono(view: View) = usoLugar.llamarTelefono(lugar)
    fun verPgWeb(view: View) = usoLugar.verPgWeb(lugar)
    fun ponerDeGaleria(view: View)= usoLugar.ponerDeGaleria(RESULTADO_GALERIA)
    fun tomarFoto(view: View) { uriUltimaFoto = usoLugar.tomarFoto(RESULTADO_FOTO)!!
    }
    fun eliminarFoto(view: View) = usoLugar.ponerFoto(pos, "", foto)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULTADO_EDITAR) {
            lugar = lugares.elemento(_id)

            pos = adaptador.posicionId(_id)

            actualizaVistas()
        } else if (requestCode == RESULTADO_GALERIA) {
            if (resultCode == Activity.RESULT_OK) {
                usoLugar.ponerFoto(pos, data?.dataString ?: "", foto)
            } else {
                Toast.makeText(activity, "Foto no cargada", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == RESULTADO_FOTO) {
            if (resultCode == Activity.RESULT_OK && uriUltimaFoto!=null) {
                usoLugar.ponerFoto(pos, uriUltimaFoto.toString(), foto)
            } else {
                Toast.makeText(activity, "Error en captura", Toast.LENGTH_LONG).show()
            }
        }
    }
}