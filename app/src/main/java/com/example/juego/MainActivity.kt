package com.example.juego

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantallaCompleta(window, this)
        // Ocultar la ActionBar
        supportActionBar?.hide()
        // Evitar que se apague la pantalla
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val expVacButton= findViewById<Button>(R.id.juegos)
        val minijuegosButton= findViewById<Button>(R.id.actividades)

        expVacButton.setOnClickListener {
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("menu",0).apply()

            val intent =
                if(!getSharedPreferences("preferencias", MODE_PRIVATE).getString("rol", "").equals("")) {
                    Intent(this, MenuExperiencia::class.java)
                } else {
                    Intent(this, ExperienciaVacceaInicio::class.java)
                }

            startActivity(intent)
        }

        minijuegosButton.setOnClickListener {
            val intent = Intent(this, MinijuegosMenu::class.java)
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("menu",1).apply()
            startActivity(intent)
        }


    }
}