package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.juego.Utils.pantallaCompleta

class DressUpActivity : AppCompatActivity() {

    private val listaTunicas = listOf(
        R.drawable.tunica1,
        R.drawable.tunica2,
        R.drawable.tunica3,
        R.drawable.tunica4,
        R.drawable.tunica5,
        R.drawable.tunica6,
        R.drawable.tunica7,
        R.drawable.tunica8,
        R.drawable.tunica9
    )

    private val listaZapas = listOf(
        R.drawable.zapas1,
        R.drawable.zapas2,
        R.drawable.zapas3,
        R.drawable.zapas4,
        R.drawable.zapas5,
        R.drawable.zapas6
    )

    private val listaEscudos = listOf(
        R.drawable.escudo1,
        R.drawable.escudo2,
        R.drawable.escudo3,
        R.drawable.escudo4
    )

    private val listaObjetos = listOf(
        R.drawable.objeto1,
        R.drawable.objeto2,
        R.drawable.objeto3,
        R.drawable.objeto4,
        R.drawable.objeto5,
        R.drawable.objeto6,
        R.drawable.objeto7,
        R.drawable.objeto8,
        R.drawable.objeto9,
        R.drawable.objeto10,
        R.drawable.objeto11,
        R.drawable.objeto12
    )

    private val listaCapas = listOf(
        R.drawable.capa1,
        R.drawable.capa2,
        R.drawable.capa3,
        R.drawable.capa4,
        R.drawable.capa5,
        R.drawable.capa6,
        R.drawable.capa7,
        R.drawable.capa8,
        R.drawable.capa9,
        R.drawable.capa10,
        R.drawable.capa11,
        R.drawable.capa12
    )

    private val listaJoyas = listOf(
        R.drawable.joya1,
        R.drawable.joya2,
        R.drawable.joya3,
        R.drawable.joya4,
        R.drawable.joya5,
        R.drawable.joya6
    )

    private val listaCintos = listOf(
        R.drawable.cinto1,
        R.drawable.cinto2,
        R.drawable.cinto3,
        R.drawable.cinto4,
        R.drawable.cinto5,
        R.drawable.cinto6,
        R.drawable.cinto7
    )

    private lateinit var listaLayers: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dress)

         
        pantallaCompleta(window)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

         
        findViewById<ImageView>(R.id.botonAtras).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.botonAjustes).setOnClickListener {
            startActivity(Intent(this, AjustesApp::class.java))
        }

         
        listaLayers = arrayOf(
            findViewById(R.id.capa0),
            findViewById(R.id.capa1),
            findViewById(R.id.capa2),
            findViewById(R.id.capa3),
            findViewById(R.id.capa4),
            findViewById(R.id.capa5),
            findViewById(R.id.capa6),
            findViewById(R.id.capa7)
        )

         
        configurarBotonConPopup(R.id.escudos, listaEscudos, 1, savedInstanceState)
        configurarBotonConPopup(R.id.tunicas, listaTunicas, 2, savedInstanceState)
        configurarBotonConPopup(R.id.zapatillas, listaZapas, 3, savedInstanceState)
        configurarBotonConPopup(R.id.capas, listaCapas, 4, savedInstanceState)
        configurarBotonConPopup(R.id.cinturones, listaCintos, 5, savedInstanceState)
        configurarBotonConPopup(R.id.objetos, listaObjetos, 6, savedInstanceState)
        configurarBotonConPopup(R.id.joyas, listaJoyas, 7, savedInstanceState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        pantallaCompleta(window)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun configurarBotonConPopup(
        botonId: Int,
        lista: List<Int>,
        capa: Int,
        savedInstanceState: Bundle?
    ) {
        findViewById<ImageView>(botonId)?.setOnClickListener {
            val dialog = PopUpDress.newInstance(savedInstanceState, lista, capa)
            dialog.show(supportFragmentManager, "CustomDialogDress")
        }
    }

    fun setImagenCapa(imagen: Int, capa: Int) {
        if (capa in listaLayers.indices) {
            listaLayers[capa].setImageResource(imagen)
        }
    }
}
