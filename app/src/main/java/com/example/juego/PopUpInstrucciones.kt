package com.example.juego

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class PopUpInstrucciones(savedInstanceState: Bundle?) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(true) // Permitir que el diálogo se cierre al tocar fuera
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para el diálogo
        val view = inflater.inflate(R.layout.pop_up_instrucciones, container, false)

        // Configura los botones
        view.findViewById<Button>(R.id.instruccionesButton).setOnClickListener {
            dismiss()
        }

        return view
    }
}

