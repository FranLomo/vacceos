package com.example.juego

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.juego.Utils.pantallaCompleta
import com.example.juego.viewModels.Question
import com.example.juego.viewModels.TriviaViewModel

class TriviaActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answerGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var backButton: ImageView
    private lateinit var previousButton: Button
    private lateinit var frameImage: ImageView
    private lateinit var triviaViewModel: TriviaViewModel

    private lateinit var questions: Array<Question>
    private lateinit var selectedAnswers: Array<Int?>

    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

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
        triviaViewModel = ViewModelProvider(this)[TriviaViewModel::class.java]
        questions = triviaViewModel.questions
        selectedAnswers = Array(questions.size) { null }

        backButton = findViewById(R.id.botonAtrasTest)
        questionText = findViewById(R.id.preguntaTest)
        answerGroup = findViewById(R.id.opcionesTest)
        nextButton = findViewById(R.id.siguienteBoton)
        previousButton = findViewById(R.id.anteriorBoton)
        frameImage = findViewById(R.id.marcoPng)

        backButton.setOnClickListener { finish() }

        nextButton.setOnClickListener { handleNextButtonClick() }

        previousButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                updateQuestion()
                nextButton.text = "SIGUIENTE"
            }
        }

        // Muestra la primera pregunta
        updateQuestion()
    }

    private fun handleNextButtonClick() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            updateQuestion()
        } else if (nextButton.text == "FINALIZAR") {
            evaluarRespuestas()
            navegarAFinActividad()
        } else {
            mostrarMensajeFinal()
        }
    }

    private fun mostrarMensajeFinal() {
        questionText.text= "¡Fin del test!"
        answerGroup.removeAllViews()
        nextButton.text = "FINALIZAR"
    }

    private fun evaluarRespuestas() {
        correctAnswersCount = selectedAnswers.zip(questions).count { (answer, question) ->
            answer == question.correctAnswer
        }
    }

    private fun navegarAFinActividad() {
        val intent = Intent(this, FinActividad::class.java).apply {
            putExtra("titulo", getString(R.string.completado))
            putExtra("descripcion", "Has acertado $correctAnswersCount preguntas de ${questions.size}!")
        }
        startActivity(intent)
    }

    private fun updateQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionText.text = currentQuestion.text
        answerGroup.removeAllViews()

        currentQuestion.options.forEachIndexed { index, option ->
            val radioButton = crearRadioButton(option, index)
            answerGroup.addView(radioButton)
        }

        // Marca la respuesta seleccionada previamente
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
}
