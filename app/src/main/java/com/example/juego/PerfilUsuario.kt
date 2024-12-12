package com.example.juego

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PerfilUsuario : AppCompatActivity() {

    private var fotoPerfil = 0
    private var nombreChaval = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_usuario)

        configurarPantalla()
        cargarPreferencias()
        configurarBotones()
        configurarIndicadoresOriginal()
        configurarInputNombre()
    }

    private fun configurarPantalla() {
        pantallaCompleta(window, this)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()
    }

    private fun cargarPreferencias() {
        val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        fotoPerfil = sharedPreferences.getInt("fotoPerfil", R.drawable.perfil3)
        nombreChaval = sharedPreferences.getString("nombreChaval", "").orEmpty()
        actualizaFoto()
        findViewById<TextView>(R.id.inputNombre).text = nombreChaval
    }

    fun actualizaFoto() {
        fotoPerfil = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
            .getInt("fotoPerfil", R.drawable.perfil3)
        findViewById<ImageView>(R.id.fotoPerfil).setImageResource(fotoPerfil)
    }

    private fun configurarBotones() {
        findViewById<Button>(R.id.cambiarFotobutton).setOnClickListener {
            val dialog = PopUp(null)
            dialog.show(supportFragmentManager, "CustomDialog")
        }

        findViewById<ImageView>(R.id.perfilboton).setOnClickListener {
            // No hacer nada para evitar intents infinitos
        }

        findViewById<ImageView>(R.id.homeboton).setOnClickListener {
            val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
            val menu = sharedPreferences.getInt("menu", -1)
            val intent = when (menu) {
                1 -> Intent(this, MinijuegosMenu::class.java)
                0 -> Intent(this, MenuExperiencia::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }

        findViewById<ImageView>(R.id.ajustesboton).setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }
    }

    private fun configurarIndicadoresOriginal() {
        val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val type = object : TypeToken<MutableList<Int>>() {}.type

        val listaCompletadosPuzzleString = sharedPreferences.getString(
            "listaCompletadosPuzzle", "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]"
        )
        val listaCompletadosPuzzle: MutableList<Int> = Gson().fromJson(listaCompletadosPuzzleString, type)

        val listaCompletadosMazeString = sharedPreferences.getString(
            "listaCompletadosMaze", "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]"
        )
        val listaCompletadosMaze: MutableList<Int> = Gson().fromJson(listaCompletadosMazeString, type)

        val listaCompletadosDigString = sharedPreferences.getString(
            "listaCompletadosDig", "[0,0,0,0,0,0,0,0,0,0,0,0]"
        )
        val listaCompletadosDig: MutableList<Int> = Gson().fromJson(listaCompletadosDigString, type)

        val porcentajePuzzle = listaCompletadosPuzzle.count { it == 1 } / listaCompletadosPuzzle.size.toFloat() * 100
        val porcentajeMaze = listaCompletadosMaze.count { it == 1 } / listaCompletadosMaze.size.toFloat() * 100
        val porcentajeDig = listaCompletadosDig.count { it == 1 } / listaCompletadosDig.size.toFloat() * 100

        println(listaCompletadosDig)

        findViewById<TextView>(R.id.porcentaje_puzzle).text =
            getString(R.string.nivelPuzzle) + String.format("%.2f", porcentajePuzzle) + "%"
        findViewById<TextView>(R.id.porcentaje_laberinto).text =
            getString(R.string.nivelLaberinto) + String.format("%.2f", porcentajeMaze) + "%"
        findViewById<TextView>(R.id.porcentaje_excavar).text =
            getString(R.string.nivelExcavar) + String.format("%.2f", porcentajeDig) + "%"
    }

    private fun configurarInputNombre() {
        val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val inputNombre = findViewById<TextView>(R.id.inputNombre)

        inputNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                sharedPreferences.edit().putString("nombreChaval", s.toString()).apply()
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        pantallaCompleta(window, this)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
