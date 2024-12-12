package com.example.juego

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.juego.Utils.pantallaCompleta

class FinActividad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fin_actividad)
        pantallaCompleta(window, this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()
         
        val extras = intent.extras
        val titulo = extras?.getString("titulo")!!
        val descripcion = extras.getString("descripcion")!!
        val foto = extras.getInt("foto", R.drawable.blank)

        configurarInterfaz(titulo, descripcion, foto)

        configurarBotones()
    }

    private fun configurarInterfaz(titulo: String, descripcion: String, foto: Int) {
        val imagenGanar = findViewById<ImageView>(R.id.imagenGanar)
        val textoGanar = findViewById<TextView>(R.id.textoGanar)
        val tituloGanar = findViewById<TextView>(R.id.titulo)

         
        tituloGanar.textSize = 24f
        tituloGanar.text = titulo
        textoGanar.text = descripcion

         
        if (foto == R.drawable.blank) {
            val fotoPinto = findViewById<ImageView>(R.id.fotoPinto)
            fotoPinto.layoutParams.apply {
                height = 600
                width = 600
            }

            imagenGanar.layoutParams.apply {
                height = 1
                width = 1
            }
        } else {
            imagenGanar.setImageDrawable(ContextCompat.getDrawable(this, foto))
        }
    }

    private fun configurarBotones() {
        val siguienteBoton = findViewById<Button>(R.id.siguienteButton)
        val atrasBoton = findViewById<ImageView>(R.id.botonAtras)
        val ajustesButton = findViewById<ImageView>(R.id.botonAjustes)

        atrasBoton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("resultKey", "resultValue")
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        ajustesButton.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }

        siguienteBoton.setOnClickListener {
            val menu = getSharedPreferences("preferencias", MODE_PRIVATE).getInt("menu", 1)
            val intent = if (menu == 1) {
                val minijuego = getSharedPreferences("preferencias", MODE_PRIVATE).getInt("minijuego", -1)
                when (minijuego) {
                    0 -> Intent(this, MenuNivelesPuzzle::class.java)
                    1 -> Intent(this, MenuNivelesMaze::class.java)
                    2 -> Intent(this, MenuNivelesPaint::class.java)
                    3 -> Intent(this, MenuNivelesDig::class.java)
                    else -> Intent(this, MinijuegosMenu::class.java)
                }
            } else {
                Intent(this, MenuExperiencia::class.java)
            }
            startActivity(intent)
        }
    }
}
