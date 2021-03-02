package com.e.mislugares.presentacion

import android.app.Activity
import android.os.Bundle

class PreferenciasActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, PreferenciasFragment())
                .commit()
    }
}
