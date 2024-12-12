package com.example.juego

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class NivelesDificultad : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.niveles_dificultad)

        pantallaCompleta(window)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val sharedPref= getSharedPreferences("preferencias", MODE_PRIVATE)

        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener {
            finish()
        }

        val botonAjustes = findViewById<ImageView>(R.id.botonAjustes)
        botonAjustes.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }

        val titulo= findViewById<TextView>(R.id.titulo)
        if(sharedPref.getInt("minijuego",-1)==0) {
            titulo.text = getString(R.string.puzzle_button)
        } else if(sharedPref.getInt("minijuego",-1)==1) {
            titulo.text = getString(R.string.laberinto_button)
        }

        val nivelFacil = findViewById<Button>(R.id.facil_button)
        val nivelMedio = findViewById<Button>(R.id.medio_button)
        val nivelDificil = findViewById<Button>(R.id.dificil_button)
        definingDificulty(sharedPref, nivelFacil, "facil", -1, -1)
        definingDificulty(sharedPref, nivelMedio, "medio", -1, -1)
        definingDificulty(sharedPref, nivelDificil, "dificil", 0, 0)


    }
    private fun definingDificulty(
        sharedPref: SharedPreferences,
        buttonDif: Button,
        type : String,
        num1: Int,
        num2: Int
    ){
        buttonDif.setOnClickListener {
            sharedPref.edit().putString("dificultad", type).apply()
            if (sharedPref.getInt("minijuego", num1) == 0) {
                val intent = Intent(this, PuzzleActivity::class.java)
                startActivity(intent)
            } else if (sharedPref.getInt("minijuego", num2) == 1) {
                val intent = Intent(this, MazeActivity::class.java)
                startActivity(intent)
            }
        }

    }
}