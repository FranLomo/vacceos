package com.example.juego

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.juego.Utils.dividirImagenPuzzle
import com.example.juego.Utils.pantallaCompleta
import com.example.juego.viewModels.PuzzleViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class PuzzleActivity : AppCompatActivity() {

    private lateinit var puzzleGrid: GridLayout
    private var selectedPiece: ImageView? = null
    private lateinit var puzzleViewModel: PuzzleViewModel
    private lateinit var imagenes: Array<Bitmap?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)

        pantallaCompleta(window, this)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        inicializarComponentes()
    }

    private fun inicializarComponentes() {
        puzzleGrid = findViewById(R.id.puzzle_grid)

        val sharedPref = getSharedPreferences("preferencias", MODE_PRIVATE)
        val numNivel = sharedPref.getInt("numNivel", 0)
        val foto = sharedPref.getInt("nivel", 0)
        val descripcion = sharedPref.getString("descripcion", "¡Enhorabuena!")

        puzzleViewModel = ViewModelProvider(this)[PuzzleViewModel::class.java]

        configurarDificultad(sharedPref, foto)

        setupPuzzleGrid()

        findViewById<ImageView>(R.id.ayuda).setOnClickListener { mostrarAyuda(foto) }

        puzzleViewModel.isPuzzleSolved.observe(this) { solved ->
            if (solved) resolverPuzzle(numNivel, sharedPref, foto, descripcion!!)
        }

        findViewById<ImageView>(R.id.botonAtras).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.botonAjustes).setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }
    }

    private fun configurarDificultad(sharedPref: android.content.SharedPreferences, foto: Int) {
        val dificultad = sharedPref.getString("dificultad", "facil")
        when (dificultad) {
            "facil" -> {
                puzzleGrid.rowCount = 4
                puzzleGrid.columnCount = 3
                imagenes = dividirImagenPuzzle(this, foto, 4, 3)
                puzzleViewModel.setNumPiezas(12)
            }
            "medio" -> {
                puzzleGrid.rowCount = 8
                puzzleGrid.columnCount = 6
                imagenes = dividirImagenPuzzle(this, foto, 8, 6)
                puzzleViewModel.setNumPiezas(48)
            }
            else -> {
                puzzleGrid.rowCount = 12
                puzzleGrid.columnCount = 9
                imagenes = dividirImagenPuzzle(this, foto, 12, 9)
                puzzleViewModel.setNumPiezas(108)
            }
        }
    }

    private fun setupPuzzleGrid() {
        val pieces = puzzleViewModel.puzzlePieces.value!!

        for (i in pieces) {
            val imageView = crearImageView(i)
            puzzleGrid.addView(imageView)
        }
    }

    private fun crearImageView(id: Int): ImageView {
        return ImageView(this).apply {
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = 0
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }
            this.id = id
            setImageBitmap(imagenes[id])
            setPadding(2, 2, 2, 2)
            setBackgroundColor(Color.WHITE)
            scaleType = ImageView.ScaleType.CENTER_CROP
            isClickable = true
            isFocusable = true
            setOnClickListener { onPieceClicked(this) }
        }
    }

    private fun mostrarAyuda(foto: Int) {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.pop_up_ayuda, null)
        view.findViewById<ImageView>(R.id.fotoAyuda).setImageResource(foto)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun resolverPuzzle(
        numNivel: Int,
        sharedPref: android.content.SharedPreferences,
        foto: Int,
        descripcion: String
    ) {
        val listaCompletadosString = sharedPref.getString(
            "listaCompletadosPuzzle", "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]"
        )
        val type = object : TypeToken<MutableList<Int>>() {}.type
        val listaCompletados: MutableList<Int> = Gson().fromJson(listaCompletadosString, type)

        val dificultadNumero = when (puzzleViewModel.getNumPiezas()) {
            12 -> 0
            48 -> 1
            else -> 2
        }
        listaCompletados[numNivel + 6 * dificultadNumero] = 1

        sharedPref.edit().putString("listaCompletadosPuzzle", JSONArray(listaCompletados).toString()).apply()

        val intent = Intent(this, FinActividad::class.java).apply {
            putExtra("titulo", getString(R.string.completado))
            putExtra("descripcion", descripcion)
            putExtra("foto", foto)
        }
        startActivity(intent)
    }

    private fun onPieceClicked(imageView: ImageView) {
        if (selectedPiece == null) {
            selectedPiece = imageView
            imageView.alpha = 0.5f
        } else {
            intercambiarPiezas(imageView)
            selectedPiece!!.alpha = 1f
            selectedPiece = null
            puzzleViewModel.checkIfSolved(puzzleGrid)
        }
    }

    private fun intercambiarPiezas(imageView: ImageView) {
        val tempId = selectedPiece!!.id
        selectedPiece!!.id = imageView.id
        imageView.id = tempId
        selectedPiece!!.setImageBitmap(imagenes[selectedPiece!!.id])
        imageView.setImageBitmap(imagenes[imageView.id])
    }
}
