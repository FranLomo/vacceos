package com.example.juego


import android.content.Context
import android.content.Intent
//import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
//import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.juego.Utils.pantallaCompleta
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import com.example.juego.viewModels.DigViewModel
import kotlin.math.sqrt

class DigActivity : AppCompatActivity() {

    private lateinit var layout : RelativeLayout
    private lateinit var digViewModel : DigViewModel
    private var pause = false
    //private lateinit var launcher: ActivityResultLauncher<Intent> //no se usa
    private val listaNiveles= arrayOf(
        R.drawable.dig1,
        R.drawable.dig2,
        R.drawable.dig3
    )
    private val listasTesoros = arrayOf(
        arrayOf(R.drawable.tesoro11, R.drawable.tesoro12, R.drawable.tesoro13, R.drawable.tesoro14),
        arrayOf(R.drawable.tesoro21, R.drawable.tesoro22, R.drawable.tesoro23, R.drawable.tesoro24),
        arrayOf(R.drawable.tesoro31, R.drawable.tesoro32, R.drawable.tesoro33, R.drawable.tesoro34),
    )
    private val descripcionesPorNivel= arrayOf(
        arrayOf(
            R.string.lanzas,R.string.pulseras,R.string.azadas,R.string.copas),
        arrayOf(
            R.string.espada, R.string.fibula, R.string.pendiente, R.string.jarra),
        arrayOf(
            R.string.canica, R.string.sonajero, R.string.caja, R.string.escudo)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        pantallaCompleta(window)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_dig)

        val sharedPref= getSharedPreferences("preferencias", MODE_PRIVATE)
        val numNivel= sharedPref.getInt("numNivel", 0)

        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.botonAjustes).setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }

        layout= findViewById(R.id.layoutExcavacion)

        val lienzo = Lienzo(this, this)

        layout.addView(lienzo)

        digViewModel= ViewModelProvider(this)[DigViewModel::class.java]

        layout.setBackgroundResource(listaNiveles[numNivel])
        digViewModel.setNivelDig(numNivel)

        digViewModel.found1.observe(this) { found ->
            if (found) {
                pause=true
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    var listaCompletadosString= sharedPref.getString("listaCompletadosDig", "[0,0,0,0,0,0,0,0,0,0,0,0]")
                    val type = object : TypeToken<MutableList<Int>>() {}.type
                    var listaCompletados : MutableList<Int> = Gson().fromJson(listaCompletadosString, type)

                    listaCompletados[0+numNivel*4]= 1

                    listaCompletadosString= JSONArray(listaCompletados).toString()
                    sharedPref.edit().putString("listaCompletadosDig", listaCompletadosString).apply()

                    val dialog= PopUpTreasure.newInstance(savedInstanceState, descripcion = getString(descripcionesPorNivel[numNivel][0]), foto = listasTesoros[numNivel][0])
                    dialog.show(supportFragmentManager, "CustomDialogTreasure")
                    pause= false
                }, 1000)
            }
        }

        digViewModel.found2.observe(this) { found ->
            if (found) {
                pause=true
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    var listaCompletadosString= sharedPref.getString("listaCompletadosDig", "[0,0,0,0,0,0,0,0,0,0,0,0]")
                    val type = object : TypeToken<MutableList<Int>>() {}.type
                    var listaCompletados : MutableList<Int> = Gson().fromJson(listaCompletadosString, type)

                    listaCompletados[1+numNivel*4]= 1

                    listaCompletadosString= JSONArray(listaCompletados).toString()
                    sharedPref.edit().putString("listaCompletadosDig", listaCompletadosString).apply()

                    val dialog= PopUpTreasure.newInstance(savedInstanceState, descripcion = getString(descripcionesPorNivel[numNivel][1]), foto = listasTesoros[numNivel][1])
                    dialog.show(supportFragmentManager, "CustomDialogTreasure")
                    pause= false
                }, 1000)
            }
        }

        digViewModel.found3.observe(this) { found ->
            if (found) {
                pause=true
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    var listaCompletadosString= sharedPref.getString("listaCompletadosDig", "[0,0,0,0,0,0,0,0,0,0,0,0]")
                    val type = object : TypeToken<MutableList<Int>>() {}.type
                    var listaCompletados : MutableList<Int> = Gson().fromJson(listaCompletadosString, type)

                    listaCompletados[2+numNivel*4]= 1

                    listaCompletadosString= JSONArray(listaCompletados).toString()
                    sharedPref.edit().putString("listaCompletadosDig", listaCompletadosString).apply()

                    val dialog= PopUpTreasure.newInstance(savedInstanceState, descripcion = getString(descripcionesPorNivel[numNivel][2]), foto = listasTesoros[numNivel][2])
                    dialog.show(supportFragmentManager, "CustomDialogTreasure")
                    pause= false
                }, 1000)
            }
        }

        digViewModel.found4.observe(this) { found ->
            if (found) {
                pause=true
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    var listaCompletadosString= sharedPref.getString("listaCompletadosDig", "[0,0,0,0,0,0,0,0,0,0,0,0]")
                    val type = object : TypeToken<MutableList<Int>>() {}.type
                    var listaCompletados : MutableList<Int> = Gson().fromJson(listaCompletadosString, type)

                    listaCompletados[3+numNivel*4]= 1

                    listaCompletadosString= JSONArray(listaCompletados).toString()
                    sharedPref.edit().putString("listaCompletadosDig", listaCompletadosString).apply()

                    val dialog= PopUpTreasure.newInstance(savedInstanceState, descripcion = getString(descripcionesPorNivel[numNivel][3]), foto = listasTesoros[numNivel][3])
                    dialog.show(supportFragmentManager, "CustomDialogTreasure")
                    pause= false
                }, 1000)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        pantallaCompleta(window)
        // Ocultar la ActionBar
        supportActionBar?.hide()
        // Evitar que se apague la pantalla
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    class Lienzo(context: Context, private var digActivity: DigActivity) : View(context) {

        private val paint = Paint().apply {
            color = Color.TRANSPARENT
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)  // Define la pintura para "excavar"
            style = Paint.Style.FILL
            isAntiAlias = true  // Suaviza los bordes de los círculos
        }

        private var lastX: Float? = null
        private var lastY: Float? = null

        private var scaleX : Float? = null
        private var scaleY : Float? = null

        private lateinit var bitmap: Bitmap
        private lateinit var canvasBitmap: Canvas
        private val circleRadius = 20f // Tamaño del "pincel" de excavación

        init {
            setLayerType(LAYER_TYPE_SOFTWARE, null)  // Para habilitar la transparencia
        }

        override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
            super.onSizeChanged(width, height, oldWidth, oldHeight)

            scaleX= width/100f
            scaleY= height/100f


            // Inicializa el bitmap de la misma dimensión que la vista
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            canvasBitmap = Canvas(bitmap)

            // Dibuja la tierra en el bitmap
            val backgroundPaint = Paint()
            val backgroundBitmap = BitmapFactory.decodeResource(resources, R.drawable.tierra_dig)
            canvasBitmap.drawBitmap(backgroundBitmap, null, Rect(0, 0, width, height), backgroundPaint)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            // Dibuja el bitmap en el lienzo principal
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            event?.let {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Dibuja un círculo inicial en el punto de contacto
                        canvasBitmap.drawCircle(it.x, it.y, circleRadius, paint)
                        lastX = it.x
                        lastY = it.y
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastX?.let { lastX ->
                            lastY?.let { lastY ->
                                // Dibuja círculos pequeños entre la última posición y la nueva
                                val dx = it.x - lastX
                                val dy = it.y - lastY
                                val distance = sqrt((dx * dx + dy * dy).toDouble()).toFloat()

                                // Divide el trayecto entre el último y el nuevo punto en varios pasos
                                val steps = (distance / (circleRadius / 2)).toInt()
                                val deltaX = dx / steps
                                val deltaY = dy / steps

                                for (i in 0..steps) {
                                    val x = lastX + i * deltaX
                                    val y = lastY + i * deltaY
                                    canvasBitmap.drawCircle(x, y, circleRadius, paint)
                                }
                                if(!digActivity.pause && scaleX != null && scaleY != null) {
                                    this.digActivity.digViewModel.checkIfFound(lastX, lastY, scaleX!!, scaleY!!)
                                }
                            }
                        }
                        // Actualiza la posición anterior
                        lastX = it.x
                        lastY = it.y
                        invalidate()  // Redibuja la vista
                    }
                    MotionEvent.ACTION_UP -> {
                        // Reinicia las posiciones para el próximo toque
                        lastX = null
                        lastY = null
                    }
                }
            }
            return true
        }
    }
}
