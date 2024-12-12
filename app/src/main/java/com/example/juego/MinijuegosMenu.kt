package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class MinijuegosMenu : AppCompatActivity() {

    //private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.minijuegos_menu)

        pantallaCompleta(window, this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val imageViewP = findViewById<ImageView>(R.id.buttonImageD)
        imageViewP.setOnClickListener {
            val intent = Intent(this, MenuNivelesPuzzle::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",0).apply()
            startActivity(intent)
        }
        val textViewP = findViewById<TextView>(R.id.buttonTextD)
        textViewP.setOnClickListener {
            val intent = Intent(this, MenuNivelesPuzzle::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",0).apply()
            startActivity(intent)
        }
        val imageViewL = findViewById<ImageView>(R.id.buttonImageL)
        imageViewL.setOnClickListener {
            val intent = Intent(this, MenuNivelesMaze::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",1).apply()
            startActivity(intent)
        }
        val textViewL = findViewById<TextView>(R.id.buttonTextL)
        textViewL.setOnClickListener {
            val intent = Intent(this, MenuNivelesMaze::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",1).apply()
            startActivity(intent)
        }

        val imageViewC = findViewById<ImageView>(R.id.buttonImageC)
        imageViewC.setOnClickListener {
            val intent = Intent(this, MenuNivelesPaint::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",2).apply()

            startActivity(intent)
        }
        val textViewC = findViewById<TextView>(R.id.buttonTextC)
        textViewC.setOnClickListener {
            val intent = Intent(this, MenuNivelesPaint::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",2).apply()
            startActivity(intent)
        }

        val imageViewE = findViewById<ImageView>(R.id.buttonImageE)
        imageViewE.setOnClickListener {
            val intent = Intent(this, MenuNivelesDig::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",3).apply()
            startActivity(intent)
        }
        val textViewE = findViewById<TextView>(R.id.buttonTextE)
        textViewE.setOnClickListener {
            val intent = Intent(this, DigActivity::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("minijuego",3).apply()
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
            // Nada para evitar intents infinitos
        }
        ajustesBoton.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }
    }
}