package com.example.juego

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta
import kotlin.math.max

class TestActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answerGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var backButton: ImageView
    private lateinit var previousButton: Button
    private lateinit var frameImage: ImageView

    private var contadores = Array(4) { 0 }
    private var maximo = 0

    private val questions = arrayOf(
        Question(R.string.qgenero, arrayOf(R.string.chico, R.string.chica), 0),
        Question(R.string.qpreferencia, arrayOf(R.string.campo, R.string.ciudad), 0),
        Question(R.string.qgusta, arrayOf(R.string.equipo,R.string.mascotas,R.string.compras,R.string.manualidades), 0),
        Question(R.string.qmayor, arrayOf(R.string.militar, R.string.jardinero, R.string.presidente, R.string.alfarero), 0),
        Question(R.string.qjugar, arrayOf(R.string.competir,R.string.deportes, R.string.estrategia,R.string.ensenar ), 0),
        Question(R.string.qorganizar, arrayOf(R.string.listo, R.string.aburrirme, R.string.horario, R.string.proyectos), 0),
        Question(R.string.qimportante, arrayOf(R.string.decisiones, R.string.rapido, R.string.contentos, R.string.divertido), 0),
        Question(R.string.qdificil, arrayOf(R.string.pienso,R.string.resolver ,R.string.analizar ,R.string.buscar ), 0),
        Question(R.string.qfin, arrayOf(R.string.fuerte, R.string.orden, R.string.amigo, R.string.genial ), 0)
    )

    private var selectedAnswers = arrayOfNulls<Int>(questions.size)
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio_test)

        configurarPantalla()
        inicializarComponentes()
    }

    private fun configurarPantalla() {
        pantallaCompleta(window, this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()
    }

    private fun inicializarComponentes() {
        backButton = findViewById(R.id.botonAtrasTest)
        questionText = findViewById(R.id.preguntaTest)
        answerGroup = findViewById(R.id.opcionesTest)
        nextButton = findViewById(R.id.siguienteBoton)
        previousButton = findViewById(R.id.anteriorBoton)
        frameImage = findViewById(R.id.marcoPng)

        updateQuestion()

        backButton.setOnClickListener { finish() }

        nextButton.setOnClickListener { handleNextButtonClick() }

        previousButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                updateQuestion()
            }
        }
    }

    private fun handleNextButtonClick() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            updateQuestion()
        } else if (nextButton.text == getString(R.string.finalizar)) {
            calcularResultado()
            val intent = Intent(this, ResultadoTest::class.java)
            startActivity(intent)
        } else {
            questionText.text= getString(R.string.fintest)
            answerGroup.removeAllViews()
            nextButton.text = getString(R.string.finalizar)
        }
    }

    private fun updateQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionText.text = getString(currentQuestion.text)
        answerGroup.removeAllViews()

        currentQuestion.options.forEachIndexed { index, option ->
            val radioButton = crearRadioButton(getString(option), index)
            answerGroup.addView(radioButton)
        }

        selectedAnswers[currentQuestionIndex]?.let { answerGroup.check(answerGroup.getChildAt(it).id) }
    }

    private fun crearRadioButton(option: String, index: Int): RadioButton {
        return RadioButton(this).apply {
            text = option
            textSize = 20f
            typeface = Typeface.create(typeface, Typeface.BOLD)
            setTextColor(Color.WHITE)
            isChecked = selectedAnswers[currentQuestionIndex] == index

            setOnClickListener { selectedAnswers[currentQuestionIndex] = index }
        }
    }

    private fun calcularResultado() {
        val sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE)

        contadores.fill(0)

        if (selectedAnswers[0] == 0) {
            sharedPreferences.edit().putString("genero", "chico").apply()
        } else {
            sharedPreferences.edit().putString("genero", "chica").apply()
        }

        if (selectedAnswers[1] == 0) {
            contadores[0]++
            contadores[1]++
        } else {
            contadores[2]++
            contadores[3]++
        }

        for (i in 2 until selectedAnswers.size) {
            contadores[selectedAnswers[i] ?: 1]++
        }

        maximo = contadores.maxOrNull() ?: 0
        val rol = when (contadores.indexOf(maximo)) {
            0 -> "guerrero"
            1 -> "campesino"
            2 -> "consejo"
            3 -> "artesano"
            else -> "campesino"
        }
        sharedPreferences.edit().putString("rol", rol).apply()
    }

    data class Question(val text: Int, val options: Array<Int>, val answerIndex: Int)
}
