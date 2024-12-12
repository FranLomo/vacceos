package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class ExperienciaVacceaInicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.experiencia_inicio)

         
        configurarPantalla()

         
        configurarBotones()
    }

    private fun configurarPantalla() {
        pantallaCompleta(window, this)
        supportActionBar?.hide()  
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)  
    }

    private fun configurarBotones() {
         
        findViewById<ImageView>(R.id.botonAtras)?.setOnClickListener {
            finish()
        }

         
        findViewById<ImageView>(R.id.bienvenida)?.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }
}
