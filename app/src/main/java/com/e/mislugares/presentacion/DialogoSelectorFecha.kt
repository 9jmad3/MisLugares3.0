package com.e.mislugares.presentacion

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogoSelectorFecha : DialogFragment() {
    private var escuchador: DatePickerDialog.OnDateSetListener? = null
    fun setOnDateSetListener(escuchador: DatePickerDialog.OnDateSetListener) {
        this.escuchador = escuchador
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario = Calendar.getInstance()
        val fecha = getArguments()?.getLong("fecha")?:0L
        calendario.setTimeInMillis(fecha)
        val año = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(getActivity()!!.applicationContext, escuchador, año, mes, dia)
    }
}
