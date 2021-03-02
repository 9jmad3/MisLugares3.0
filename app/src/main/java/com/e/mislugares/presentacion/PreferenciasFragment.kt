package com.e.mislugares.presentacion

import android.os.Bundle
import android.preference.PreferenceFragment
import com.e.mislugares.R

class PreferenciasFragment : PreferenceFragment() { //Deprecated
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferencias)
    }
}