package com.e.mislugares.presentacion

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.e.mislugares.R
import com.e.mislugares.casos_uso.CasosUsoActividades
import com.e.mislugares.casos_uso.CasosUsoLugar
import com.e.mislugares.casos_uso.CasosUsosLocalizacion
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val SOLICITUD_PERMISO_WRITE_CALL_LOG = 0
    val SOLICITUD_PERMISO_LOCALIZACION = 1
    val usoLocalizacion by lazy {
        CasosUsosLocalizacion(this, SOLICITUD_PERMISO_LOCALIZACION) }
    val usoActividades by lazy { CasosUsoActividades(this) }
    val RESULTADO_PREFERENCIAS = 0
    val lugares by lazy { (application as Aplicacion).lugares }
    val usoLugar by lazy { CasosUsoLugar(this, null,lugares, adaptador) }
    val adaptador by lazy { (application as Aplicacion).adaptador }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            usoLugar.nuevo()
        }




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.action_settings -> {
                //lanzarPreferencias()
                usoActividades.lanzarPreferencias(RESULTADO_PREFERENCIAS)
                true
            }
            R.id.acercaDe -> {
                usoActividades.lanzarAcerdaDe()
                true
            }
            R.id.menu_buscar -> {
                lanzarVistaLugar()
                true
            }

            R.id.menu_mapa -> {
                usoActividades.lanzarMapa()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun lanzarAcercaDe(view: View? = null) {
        val i = Intent(this, AcercaDeActivity::class.java)
        startActivity(i)
    }

    fun lanzarPreferencias(view: View? = null) {
        val i = Intent(this, PreferenciasActivity::class.java)
        startActivity(i)
    }

    fun lanzarVistaLugar(view: View? = null) {
        val entrada = EditText(this)
        entrada.setText("0")
        AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok") { dialog, whichButton ->
                    val id = Integer.parseInt(entrada.text.toString())
                    usoLugar.mostrar(id);
                }
                .setNegativeButton("Cancelar", null)
                .show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        usoLocalizacion.activar()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {
        usoLocalizacion.desactivar()
        super.onPause()
    }
    override fun onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
        super.onStop()
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray ) {
        if (requestCode == SOLICITUD_PERMISO_LOCALIZACION
                && grantResults.size == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
        )
            usoLocalizacion.permisoConcedido()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULTADO_PREFERENCIAS) {
            adaptador.cursor = lugares.extraeCursor()
            adaptador.notifyDataSetChanged()
            if (usoLugar.obtenerFragmentVista() != null)
                usoLugar.mostrar(0)
        }
    }


}