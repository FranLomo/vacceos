package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class MenuExperiencia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.experiencia_menu)

        pantallaCompleta(window, this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val perfilBoton = findViewById<ImageView>(R.id.perfilboton)
        val homeBoton = findViewById<ImageView>(R.id.homeboton)
        val ajustesBoton = findViewById<ImageView>(R.id.ajustesboton)

        perfilBoton.setOnClickListener {
            val intent = Intent(this, PerfilUsuario::class.java)
            startActivity(intent)
        }
        homeBoton.setOnClickListener {
            // Nada para evitar llamadas infinitas
        }
        ajustesBoton.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }

        val juegos = findViewById<Button>(R.id.juegos)
        juegos.setOnClickListener {
            val intent = Intent(this, JuegosMenu::class.java)
            startActivity(intent)
        }

        val actividades = findViewById<Button>(R.id.actividades)
        actividades.setOnClickListener {
            val intent = Intent (this, ActividadesMenu::class.java)
            startActivity(intent)
        }

        val reiniciar = findViewById<Button>(R.id.reiniciar)
        reiniciar.setOnClickListener {
            val intent = Intent (this, ExperienciaVacceaInicio::class.java)
            startActivity(intent)
        }

    }
}