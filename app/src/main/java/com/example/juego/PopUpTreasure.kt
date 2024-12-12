package com.example.juego

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class PopUpTreasure(savedInstanceState: Bundle?) : DialogFragment() {

    companion object {
        fun newInstance(savedInstanceState: Bundle?, descripcion: String, foto : Int): PopUpTreasure {
            val popUp = PopUpTreasure(savedInstanceState)
            val args = Bundle()

            args.putString("descripcion", descripcion)
            args.putInt("foto", foto)
            popUp.arguments = args

            return popUp
        }
    }

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
        val view = inflater.inflate(R.layout.pop_up_tesoro, container, false)
        val foto= arguments?.getInt("foto")
        println("FOTO: "+foto)
        val descripcion= arguments?.getString("descripcion")

        val fotoTesoro= view.findViewById<ImageView>(R.id.fotoTesoro)
        val textoDescripcion= view.findViewById<TextView>(R.id.textoDescripcion)

        fotoTesoro.setImageResource(foto!!)
        textoDescripcion.text = descripcion

        view.findViewById<Button>(R.id.seguirButton).setOnClickListener{
            dismiss()
        }



        return view
    }
}