package com.example.juego

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.*
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class PaintActivity : AppCompatActivity() {

    private lateinit var layout: RelativeLayout
    private lateinit var botones: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        configurarPantalla()

        val lienzo = Lienzo(this)
        layout = findViewById(R.id.layoutPintar)
        val dibujo = getSharedPreferences("preferencias", MODE_PRIVATE).getInt("nivel", 0)
        lienzo.setDibujo(dibujo)
        layout.addView(lienzo)

        findViewById<ImageView>(R.id.botonAtras).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.botonAjustes).setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }

        botones = arrayOf(
            findViewById(R.id.azul), findViewById(R.id.rosa), findViewById(R.id.blanco),
            findViewById(R.id.morado), findViewById(R.id.amarillo), findViewById(R.id.azulclaro),
            findViewById(R.id.verde), findViewById(R.id.naranja), findViewById(R.id.rojo)
        )

        configurarBotonesColores(lienzo)
        configurarBotonPincel(lienzo)
    }

    private fun configurarPantalla() {
        pantallaCompleta(window, this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        findViewById<ImageView>(R.id.botonAtras).setOnClickListener { finish() }
    }

    private fun configurarBotonesColores(lienzo: Lienzo) {
        val colores = mapOf(
            R.id.rojo to Color.rgb(209, 8, 1),
            R.id.naranja to Color.rgb(255, 114, 1),
            R.id.amarillo to Color.rgb(255, 196, 1),
            R.id.verde to Color.rgb(120, 194, 5),
            R.id.azulclaro to Color.rgb(0, 197, 209),
            R.id.azul to Color.rgb(1, 59, 255),
            R.id.morado to Color.rgb(160, 1, 204),
            R.id.rosa to Color.rgb(255, 71, 182),
            R.id.blanco to Color.rgb(255, 255, 255)
        )

        for ((botonId, color) in colores) {
            findViewById<ImageView>(botonId).setOnClickListener {
                cambiarColor(lienzo, botonId, color)
            }
        }
    }

    private fun cambiarColor(lienzo: Lienzo, botonId: Int, color: Int) {
        lienzo.setPaintColor(color)
        for (boton in botones) {
            boton.setPadding(0, 0, 0, 0)
        }
        findViewById<ImageView>(botonId).setPadding(15, 15, 15, 15) // Ajusta visualmente el botón seleccionado
    }

    private fun configurarBotonPincel(lienzo: Lienzo) {
        val pincelboton = findViewById<ImageView>(R.id.pincel)
        findViewById<ImageView>(R.id.pincel).setOnClickListener {


            var nuevoTamaño = when (lienzo.getStrokeWidth()) {
                30f -> 50f
                50f -> 75f
                75f -> 100f
                100f -> 30f
                else -> 30f
            }
            lienzo.setStrokeWidth(nuevoTamaño)
            val nuevoTamanoBoton = when (nuevoTamaño) {
                30f -> 150 // Tamaño pequeño
                50f -> 160 // Tamaño mediano
                75f -> 170 // Tamaño grande
                100f -> 180 // Tamaño extra grande
                else -> 150
            }
            val layoutParams = pincelboton.layoutParams
            layoutParams.width = nuevoTamanoBoton
            layoutParams.height = nuevoTamanoBoton
            pincelboton.layoutParams = layoutParams
        }


    }

    class Lienzo(context: Context) : View(context) {

        private var path: Path = Path()
        private val paths = mutableListOf<Pair<Path, Paint>>()

        private val paint = Paint().apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = 30f
            isAntiAlias = true
        }

        private var dibujo = 0
        private lateinit var bitmap: Bitmap
        private lateinit var canvasBitmap: Canvas
        private lateinit var siluetaBitmap: Bitmap

        init {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }

        override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
            super.onSizeChanged(width, height, oldWidth, oldHeight)

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            canvasBitmap = Canvas(bitmap)

            siluetaBitmap = BitmapFactory.decodeResource(resources, dibujo)
            siluetaBitmap = Bitmap.createScaledBitmap(siluetaBitmap, width, height, true)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            for ((p, customPaint) in paths) {
                canvas.drawPath(p, customPaint)
            }
            canvas.drawPath(path, paint)
            canvas.drawBitmap(siluetaBitmap, 0f, 0f, null)
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> path.moveTo(event.x, event.y)
                MotionEvent.ACTION_MOVE -> {
                    path.lineTo(event.x, event.y)
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    paths.add(Pair(Path(path), Paint(paint)))
                    path.reset()
                }
            }
            return true
        }

        fun getStrokeWidth(): Float = paint.strokeWidth

        fun setStrokeWidth(width: Float) {
            paint.strokeWidth = width
        }

        fun setPaintColor(color: Int) {
            paint.color = color
        }

        fun setDibujo(dibujo: Int) {
            this.dibujo = dibujo
        }
    }
}
