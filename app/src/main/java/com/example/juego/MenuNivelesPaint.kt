package com.example.juego

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class MenuNivelesPaint : AppCompatActivity() {

    private val dibujos = arrayOf(
        R.drawable.paint1,
        R.drawable.paint2,
        R.drawable.paint3,
        R.drawable.paint4,
        R.drawable.paint5,
        R.drawable.paint6
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
        gridNiveles.rowCount= dibujos.size/2
        gridNiveles.columnCount= 2

        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setStroke(12, resources.getColor(R.color.azul))  // Establecer borde de 4dp de color rojo
        drawable.cornerRadius = 8f  // Esquinas redondeadas de 8dp

        for (i in dibujos) {
            println(i)
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
                }
                setImageResource(i)
                background = drawable

                // Ajustar la escala para que ocupe toda la casilla
                //scaleType = ImageView.ScaleType.CENTER_CROP

                isClickable= true
                isFocusable= true

                setOnClickListener {
                    getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("nivel",i).apply()
                    val intent = Intent(context, PaintActivity::class.java)
                    startActivity(intent)
                }
            }
            gridNiveles.addView(imageView)
        }

    }

}