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

class PopUp(savedInstanceState: Bundle?) : DialogFragment() {

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
        val view = inflater.inflate(R.layout.pop_up_perfil, container, false)
        val sharedPref= activity?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val foto1= view.findViewById<ImageView>(R.id.foto1)
        foto1.isClickable= true
        foto1.isFocusable= true
        foto1.setOnClickListener {
            sharedPref!!.edit().putInt("fotoPerfil", R.drawable.perfil1).apply()
            val actividad= activity as PerfilUsuario
            actividad.actualizaFoto()
            dismiss()
        }

        view.findViewById<ImageView>(R.id.foto2).setOnClickListener {
            sharedPref!!.edit().putInt("fotoPerfil", R.drawable.perfil2).apply()
            val actividad= activity as PerfilUsuario
            actividad.actualizaFoto()
            dismiss()
        }

        view.findViewById<ImageView>(R.id.foto3).setOnClickListener {
            sharedPref!!.edit().putInt("fotoPerfil", R.drawable.perfil3).apply()
            val actividad= activity as PerfilUsuario
            actividad.actualizaFoto()
            dismiss()
        }

        view.findViewById<ImageView>(R.id.foto4).setOnClickListener {
            sharedPref!!.edit().putInt("fotoPerfil", R.drawable.perfil4).apply()
            val actividad= activity as PerfilUsuario
            actividad.actualizaFoto()
            dismiss()
        }
        // Configura los botones
        view.findViewById<Button>(R.id.cerrarBoton).setOnClickListener {
            dismiss()
        }

        return view
    }
}

