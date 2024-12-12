package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.juego.Utils.pantallaCompleta
import com.example.juego.viewModels.CWViewModel

class CWActivity : AppCompatActivity() {

    private lateinit var cwGrid: GridLayout
    private lateinit var cwViewModel: CWViewModel
    private lateinit var cwArray: List<List<Char>>
    private var totalLetras = 0
    private val bordesNumerados = arrayOf(
        R.drawable.borde1,
        R.drawable.borde2,
        R.drawable.borde3,
        R.drawable.borde4,
        R.drawable.borde5,
        R.drawable.borde6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw)


        pantallaCompleta(window)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        cwGrid = findViewById(R.id.cw_grid)
        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        botonAtras.setOnClickListener { finish() }

        findViewById<ImageView>(R.id.botonAjustes).setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }

        cwViewModel = ViewModelProvider(this)[CWViewModel::class.java]


        val rol = getSharedPreferences("preferencias", MODE_PRIVATE).getString("rol", "campesino")
        rol?.let { cwViewModel.setCWPorRol(it) }


        setupCWGrid()


        findViewById<ImageView>(R.id.checkButton).setOnClickListener {
            cwViewModel.checkIfSolved(cwGrid, totalLetras)
        }


        cwViewModel.isCWSolved.observe(this) { solved ->
            if (solved) {
                val intent = Intent(this, FinActividad::class.java)
                intent.putExtra("titulo", "COMPLETADO!")
                intent.putExtra("descripcion", "Eres un máquina!")
                startActivity(intent)
            }
        }
    }

    private fun setupCWGrid() {
        cwArray = cwViewModel.cwArray.value ?: return
        cwGrid.rowCount = cwArray.size
        cwGrid.columnCount = cwArray[0].size

        val posPalabras = cwViewModel.posPalabras.value ?: return
        var contador = 0

        for (i in 0 until cwGrid.rowCount) {
            for (j in 0 until cwGrid.columnCount) {
                val editText = EditText(this)
                editText.layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }

                editText.filters = arrayOf(InputFilter.LengthFilter(1))
                editText.inputType = EditorInfo.TYPE_TEXT_FLAG_CAP_CHARACTERS
                editText.textAlignment = EditText.TEXT_ALIGNMENT_CENTER
                editText.textSize = 10f
                editText.setPadding(0, 0, 0 ,0 )
                editText.setTextColor(ContextCompat.getColor(this, R.color.black))

                if (cwArray[i][j] == ' ') {
                    editText.setBackgroundResource(R.drawable.blank)
                    editText.isFocusable = false
                    editText.isActivated = false
                } else {
                    totalLetras++
                    var esInicioPalabra = false

                    for (k in posPalabras.indices) {
                        if (i == posPalabras[k].first && j == posPalabras[k].second) {
                            editText.setBackgroundResource(bordesNumerados[k])
                            esInicioPalabra = true
                            break
                        }
                    }
                    if (!esInicioPalabra) {
                        editText.setBackgroundResource(R.drawable.borde)
                    }
                }

                editText.id = contador
                cwGrid.addView(editText)
                contador++
            }
        }


        findViewById<TextView>(R.id.horizontales1).text = cwViewModel.horizontales1
        findViewById<TextView>(R.id.horizontales2).text = cwViewModel.horizontales2
        findViewById<TextView>(R.id.horizontales3).text = cwViewModel.horizontales3
        findViewById<TextView>(R.id.verticales1).text = cwViewModel.verticales1
        findViewById<TextView>(R.id.verticales2).text = cwViewModel.verticales2
        findViewById<TextView>(R.id.verticales3).text = cwViewModel.verticales3
    }
}
