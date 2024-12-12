package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class ActividadesMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividades_menu)

        pantallaCompleta(window, this) // Método personalizado
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        configurarBotonesMenu()
        configurarBotonesActividades()
    }

    private fun configurarBotonesMenu() {

        findViewById<ImageView>(R.id.botonAtras)?.setOnClickListener {
            finish()
        }


        findViewById<ImageView>(R.id.perfilboton)?.setOnClickListener {
            abrirActividad(PerfilUsuario::class.java)
        }


        findViewById<ImageView>(R.id.homeboton)?.setOnClickListener {
            abrirActividad(MenuExperiencia::class.java)
        }


        findViewById<ImageView>(R.id.ajustesboton)?.setOnClickListener {
            abrirActividad(AjustesApp::class.java)
        }
    }

    private fun configurarBotonesActividades() {
        // Configuración de cada actividad
        findViewById<Button>(R.id.actividad1Button)?.setOnClickListener {
            abrirFinActividad(
                getString(R.string.a1),
                getString(R.string.actividad1)
            )
        }

        findViewById<Button>(R.id.actividad2Button)?.setOnClickListener {
            abrirFinActividad(
                getString(R.string.a2),
                getString(R.string.actividad2)
            )
        }

        findViewById<Button>(R.id.actividad3Button)?.setOnClickListener {
            abrirFinActividad(
                getString(R.string.a3),
                getString(R.string.actividad3)
            )
        }
    }

    private fun abrirActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun abrirFinActividad(titulo: String, descripcion: String) {
        val intent = Intent(this, FinActividad::class.java)
        intent.putExtra("titulo", titulo)
        intent.putExtra("descripcion", descripcion)
        startActivity(intent)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        pantallaCompleta(window, this)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
