package com.example.juego

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class MenuNivelesPuzzle : AppCompatActivity() {

    private val niveles = arrayOf(
        R.drawable.puzzle1,
        R.drawable.puzzle2,
        R.drawable.puzzle3,
        R.drawable.puzzle4,
        R.drawable.puzzle5,
        R.drawable.puzzle6
    )

    private val descripciones= arrayOf(
        R.string.pajaro, R.string.gallo, R.string.caballo, R.string.buitre, R.string.lobo, R.string.tintin
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_niveles)
        pantallaCompleta(window, this)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val perfilBoton = findViewById<ImageView>(R.id.perfilboton)
        val homeBoton = findViewById<ImageView>(R.id.homeboton)
        val ajustesBoton = findViewById<ImageView>(R.id.ajustesboton)

        findViewById<ImageView>(R.id.botonAtras).setOnClickListener {
            val intent = Intent(this, MinijuegosMenu::class.java)
            startActivity(intent)
        }

        perfilBoton.setOnClickListener {
            val intent = Intent(this, PerfilUsuario::class.java)
            startActivity(intent)
        }
        homeBoton.setOnClickListener {
            if(getSharedPreferences("preferencias", MODE_PRIVATE).getInt("menu",-1)==1) {
                val intent = Intent(this, MinijuegosMenu::class.java)
                startActivity(intent)
            } else if(getSharedPreferences("preferencias", MODE_PRIVATE).getInt("menu",-1)==0) {
                val intent = Intent(this, MenuExperiencia::class.java)
                startActivity(intent)
            }
        }
        ajustesBoton.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }

        val gridNiveles= findViewById<GridLayout>(R.id.gridNiveles)
        gridNiveles.rowCount= niveles.size/2
        gridNiveles.columnCount= 2

        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setStroke(12, resources.getColor(R.color.azul))  // Establecer borde de 4dp de color rojo
        drawable.cornerRadius = 8f  // Esquinas redondeadas de 8dp

        for (i in niveles.indices) {
            val imageView = ImageView(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    topMargin= 16
                    rightMargin= 16
                    bottomMargin= 16
                    leftMargin= 16
                    setPadding(12,12,12,12)
                }
                setImageResource(niveles[i])
                background = drawable

                // Ajustar la escala para que ocupe toda la casilla
                scaleType = ImageView.ScaleType.CENTER_CROP

                isClickable= true
                isFocusable= true

                setOnClickListener {
                    getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("nivel",niveles[i]).apply()
                    getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("numNivel",i).apply()
                    getSharedPreferences("preferencias", MODE_PRIVATE).edit().putString("descripcion", getString(descripciones[i])).apply()
                    val intent = Intent(context, NivelesDificultad::class.java)
                    startActivity(intent)
                }
            }
            gridNiveles.addView(imageView)
        }

    }
}