package com.example.juego

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.juego.Utils.pantallaCompleta
import com.example.juego.viewModels.MazeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MazeActivity : AppCompatActivity() {

    private lateinit var mazeGrid: GridLayout
    private lateinit var mazeViewModel: MazeViewModel
    private lateinit var gestureDetector: GestureDetector
    private val pared= R.drawable.seto
    private val hueco= R.drawable.tierra
    private val teseo= R.drawable.teseo
    private var destino = 0
    private var descripcion= ""
    private var teseoI : Int? = null
    private var teseoJ : Int? = null
    private var mazeArray : List<List<Int>>? = null
    private var rowCount= 12
    private var colCount= 12

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze)
        gestureDetector = GestureDetector(this, SwipeGestureListener(this))
        pantallaCompleta(window)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()

        val sharedPref= getSharedPreferences("preferencias", MODE_PRIVATE)

        destino= sharedPref.getInt("nivel", 0)
        descripcion= sharedPref.getString("descripcion", "").toString()

        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener {
            finish()
        }

        val botonAjustes = findViewById<ImageView>(R.id.botonAjustes)
        botonAjustes.setOnClickListener {
            val intent = Intent(this, AjustesApp::class.java)
            startActivity(intent)
        }

        mazeGrid = findViewById(R.id.maze_grid)
        mazeGrid.setOnTouchListener() { _, event ->
            gestureDetector.onTouchEvent(event) // Pasa el evento de toque al detector de gestos
            true
        }

        // Inicializa el ViewModel
        mazeViewModel = ViewModelProvider(this)[MazeViewModel::class.java]

        teseoI= mazeViewModel.startingI.value
        teseoJ= mazeViewModel.startingJ.value

        val numNivel= sharedPref.getInt("numNivel", 0)

        val dificultad= getSharedPreferences("preferencias", MODE_PRIVATE).getString("dificultad", "facil")

        var dificultadNumero= 0

        when(dificultad) {
            "facil"   -> {
                mazeViewModel.setLaberinto(0, numNivel)
                rowCount= 12
                colCount= 12
                dificultadNumero= 0
            }
            "medio"   -> {
                mazeViewModel.setLaberinto(1, numNivel)
                rowCount= 14
                colCount= 14
                dificultadNumero= 1
            }
            "dificil" -> {
                mazeViewModel.setLaberinto(2, numNivel)
                rowCount= 17
                colCount= 17
                dificultadNumero= 2
            }
        }

        // Configura el GridLayout para el puzzle (3x3)
        setupMazeGrid(rowCount, colCount)

        val dialog = PopUpInstrucciones(savedInstanceState)
        dialog.show(supportFragmentManager, "CustomDialogInstrucciones")

        mazeViewModel.isMazeSolved.observe(this) { solved ->
            if (solved) {
                var listaCompletadosString= sharedPref.getString("listaCompletadosMaze", "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]")
                val type = object : TypeToken<MutableList<Int>>() {}.type
                var listaCompletados : MutableList<Int> = Gson().fromJson(listaCompletadosString, type)

                listaCompletados[numNivel+5*dificultadNumero]= 1

                listaCompletadosString= JSONArray(listaCompletados).toString()
                sharedPref.edit().putString("listaCompletadosMaze", listaCompletadosString).apply()

                val intent= Intent(this, FinActividad::class.java)
                intent.putExtra("titulo", getString(R.string.completado))
                intent.putExtra("descripcion", descripcion)
                intent.putExtra("foto", destino)
                startActivity(intent)
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

    private fun setupMazeGrid(rowCount: Int, colCount: Int) {
        mazeGrid.rowCount = rowCount
        mazeGrid.columnCount = colCount

        // Obtén las piezas desordenadas desde el ViewModel
        mazeArray = mazeViewModel.mazeArray.value!!

        // Añade botones en el tablero con las piezas desordenadas
        var contador = 0
        for (i in 0 until mazeGrid.rowCount) {
            for (j in 0 until mazeGrid.columnCount) {
                val imageView = ImageView(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 0
                        height = 0
                        columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                        rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    }
                    scaleType = ImageView.ScaleType.CENTER_CROP // Ajustar escala para que ocupe toda la casilla
                    id = contador
                }

                when {
                    i == mazeViewModel.startingI.value && j == mazeViewModel.startingJ.value -> {
                        imageView.setBackgroundResource(hueco)
                        imageView.setImageResource(teseo)
                    }
                    i == mazeViewModel.goalI.value && j == mazeViewModel.goalJ.value -> {
                        imageView.setImageResource(destino)
                    }
                    mazeArray?.get(i)?.get(j) == 1 -> { // Pared
                        imageView.setImageResource(pared)
                    }
                    else -> {
                        imageView.setBackgroundResource(hueco)
                    }
                }

                mazeGrid.addView(imageView)
                contador++
            }
        }


    }

    fun moveRight() {
        if(mazeArray!![teseoI!!][teseoJ!!+1]==0){
            move(teseoI!!*colCount+teseoJ!!+1, 1,0)
        }
    }

    fun moveLeft() {
        if(mazeArray!![teseoI!!][teseoJ!!-1]==0){
            move( teseoI!!*colCount+teseoJ!!-1, -1,0)
        }
    }

    fun moveUp() {
        if(mazeArray!![teseoI!!-1][teseoJ!!]==0){
            move(teseoI!!*colCount-colCount+teseoJ!!, 0,-1)

        }
    }

    fun moveDown() {
        if(mazeArray!![teseoI!!+1][teseoJ!!]==0){
            move(teseoI!!*colCount+colCount+teseoJ!!,0,1)
        }

    }


    private fun move(
        index: Int,
        movJ: Int,
        movI: Int
    ){
        val antigua = mazeGrid.getChildAt(teseoI!!*colCount+teseoJ!!) as ImageView
        antigua.setImageResource(hueco)
        val nueva = mazeGrid.getChildAt(index) as ImageView
        nueva.setImageResource(teseo)
        teseoJ= teseoJ!! + movJ
        teseoI= teseoI!! + movI
        mazeViewModel.chekIfSolved(teseoI!!, teseoJ!!)

    }
}
