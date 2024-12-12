package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class ResultadoTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resultado_test)
        pantallaCompleta(window)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        configurarTextoRol()
        configurarBotones()
    }

    private fun configurarTextoRol() {
        val sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)
        val genero = sharedPreferences.getString("genero", "chico")
        val rol = sharedPreferences.getString("rol", "campesino")
        val textoResultado = findViewById<TextView>(R.id.textoGanar)

        val textoRol = when (rol) {
            "guerrero" -> getString(R.string.guerreros)
            "campesino" -> getString(R.string.campesino)
            "consejo" -> getString(R.string.consejero)
            "artesano" -> getString(R.string.artesano)
            else -> getString(R.string.campesino)
        }

        textoResultado.text = textoRol
    }

    private fun configurarBotones() {
        val atrasBoton = findViewById<ImageView>(R.id.botonAtras)
        atrasBoton.setOnClickListener { finish() }

        val ajustesBoton = findViewById<ImageView>(R.id.botonAjustes)
        ajustesBoton.setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }

        val siguienteBoton = findViewById<Button>(R.id.siguienteButton)
        siguienteBoton.setOnClickListener {
            startActivity(Intent(this, MenuExperiencia::class.java))
        }
    }
}
