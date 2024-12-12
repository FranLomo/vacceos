package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class JuegosMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.juegos_menu)

        pantallaCompleta(window)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener {
            finish()
        }

        val imageViewD = findViewById<ImageView>(R.id.buttonImageD)
        imageViewD.setOnClickListener(){
            val intent = Intent(this, DressUpActivity::class.java)
            startActivity(intent)
        }
        val textViewD = findViewById<TextView>(R.id.buttonTextD)
        textViewD.setOnClickListener(){
            val intent = Intent(this, DressUpActivity::class.java)
            startActivity(intent)
        }

        val imageViewT = findViewById<ImageView>(R.id.buttonImageT)
        imageViewT.setOnClickListener(){
            val intent = Intent(this, TriviaActivity::class.java)
            startActivity(intent)
        }
        val textViewT = findViewById<TextView>(R.id.buttonTextT)
        textViewT.setOnClickListener(){
            val intent = Intent(this, TriviaActivity::class.java)
            startActivity(intent)
        }

        val imageViewCr = findViewById<ImageView>(R.id.buttonImageCr)
        imageViewCr.setOnClickListener(){
            val intent = Intent(this, CWActivity::class.java)
            startActivity(intent)
        }
        val textViewCr = findViewById<TextView>(R.id.buttonTextCr)
        textViewCr.setOnClickListener(){
            val intent = Intent(this, CWActivity::class.java)
            startActivity(intent)
        }

        // Configurar los botones de la barra de navegación inferior
        val perfilBoton = findViewById<ImageView>(R.id.perfilboton)
        val homeBoton = findViewById<ImageView>(R.id.homeboton)
        val ajustesBoton = findViewById<ImageView>(R.id.ajustesboton)

        perfilBoton.setOnClickListener {
            val intent = Intent(this, PerfilUsuario::class.java)
            startActivity(intent)
        }
        homeBoton.setOnClickListener {
            val intent = Intent(this, MenuExperiencia::class.java)
            startActivity(intent)
        }
        ajustesBoton.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }
    }


}