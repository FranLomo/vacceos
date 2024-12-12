package com.example.juego

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.setPadding
import com.example.juego.Utils.pantallaCompleta
import java.util.Locale

class AjustesApp : AppCompatActivity() {

    private lateinit var audioManager: AudioManager
    private var volumenGuardado: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajustes_aplicacion)

        // Configuración inicial de la pantalla
        pantallaCompleta(window, this)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Inicializar botones
        val btnRestaurar = findViewById<Button>(R.id.btnRestaurar)
        val btnSilenciar = findViewById<Button>(R.id.btnSilenciar)
        val btnMusica = findViewById<Button>(R.id.btnMusica)
        val btnNoMusica = findViewById<Button>(R.id.btnNoMusica)
        val botonAtras = findViewById<ImageView>(R.id.botonAtras)
        val perfilUsuario = findViewById<ImageView>(R.id.perfilboton)
        val homeBoton = findViewById<ImageView>(R.id.homeboton)
        val spanish = findViewById<ImageView>(R.id.spanish)
        val english = findViewById<ImageView>(R.id.english)

        // Inicializar AudioManager
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        // Configurar colores iniciales de los botones
        actualizarColoresBotones(btnRestaurar, btnSilenciar, btnMusica, btnNoMusica, spanish, english)

        // Configurar acciones de botones
        botonAtras.setOnClickListener {
            finish()
        }

        homeBoton.setOnClickListener {
            val sharedPref = getSharedPreferences("preferencias", MODE_PRIVATE)
            val menu = sharedPref.getInt("menu", -1)
            val intent = if (menu == 1) {
                Intent(this, MinijuegosMenu::class.java)
            } else {
                Intent(this, MenuExperiencia::class.java)
            }
            startActivity(intent)
        }

        perfilUsuario.setOnClickListener {
            val intent= Intent(this, PerfilUsuario::class.java)
            startActivity(intent)
        }

        btnSilenciar.setOnClickListener {
            silenciarDispositivo(btnRestaurar, btnSilenciar)
        }

        btnRestaurar.setOnClickListener {
            restaurarVolumen(btnRestaurar, btnSilenciar)
        }

        btnMusica.setOnClickListener {
            iniciarMusica(btnMusica, btnNoMusica)
        }

        btnNoMusica.setOnClickListener {
            detenerMusica(btnMusica, btnNoMusica)
        }

        spanish.setOnClickListener {
            setIdioma("es")
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("idioma",0).apply()
            spanish.setPadding(-20)
            english.setPadding(0)
        }

        english.setOnClickListener {
            setIdioma("en")
            getSharedPreferences("preferencias", MODE_PRIVATE).edit().putInt("idioma",1).apply()
            english.setPadding(-20)
            spanish.setPadding(0)
        }
    }

    fun setIdioma(codigo: String) {
        val locale = Locale(codigo)
        Locale.setDefault(locale)

        val displayMetrics= resources.displayMetrics
        val config= resources.configuration
        config.setLocale(Locale(codigo))
        resources.updateConfiguration(config, displayMetrics)
        config.locale= Locale(codigo)
        resources.updateConfiguration(config, displayMetrics)
    }

    private fun actualizarColoresBotones(btnRestaurar: Button, btnSilenciar: Button, btnMusica: Button, btnNoMusica: Button, spanish: ImageView, english: ImageView) {
        if (MusicService.isPlayingMusic) {
            btnMusica.setBackgroundColor(("#EA9037").toColorInt())
            btnNoMusica.setBackgroundColor(("#309CA2").toColorInt())
        } else {
            btnMusica.setBackgroundColor(("#309CA2").toColorInt())
            btnNoMusica.setBackgroundColor(("#EA9037").toColorInt())
        }

        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
            btnRestaurar.setBackgroundColor(("#EA9037").toColorInt())
            btnSilenciar.setBackgroundColor(("#309CA2").toColorInt())
        } else {
            btnRestaurar.setBackgroundColor(("#309CA2").toColorInt())
            btnSilenciar.setBackgroundColor(("#EA9037").toColorInt())
        }
        if(getSharedPreferences("preferencias", MODE_PRIVATE).getInt("idioma",0)==0) {
            spanish.setPadding(-20)
        } else {
            english.setPadding(-20)
        }
    }

    private fun silenciarDispositivo(btnRestaurar: Button, btnSilenciar: Button) {
        volumenGuardado = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        getSharedPreferences("preferencias", MODE_PRIVATE)
            .edit()
            .putInt("volumenGuardado", volumenGuardado)
            .apply()

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        btnRestaurar.setBackgroundColor(("#309CA2").toColorInt())
        btnSilenciar.setBackgroundColor(("#EA9037").toColorInt())
    }

    private fun restaurarVolumen(btnRestaurar: Button, btnSilenciar: Button) {
        volumenGuardado = getSharedPreferences("preferencias", MODE_PRIVATE).getInt("volumenGuardado", 0)
        if (volumenGuardado > 0) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumenGuardado, 0)
            btnSilenciar.setBackgroundColor(("#309CA2").toColorInt())
            btnRestaurar.setBackgroundColor(("#EA9037").toColorInt())
        }
    }

    private fun iniciarMusica(btnMusica: Button, btnNoMusic: Button) {
        btnNoMusic.setBackgroundColor(("#309CA2").toColorInt())
        btnMusica.setBackgroundColor(("#EA9037").toColorInt())
        val musicServiceIntent = Intent(this, MusicService::class.java)
        startService(musicServiceIntent)
    }

    private fun detenerMusica(btnMusica: Button, btnNoMusic: Button) {
        btnMusica.setBackgroundColor(("#309CA2").toColorInt())
        btnNoMusic.setBackgroundColor(("#EA9037").toColorInt())
        val musicServiceIntent = Intent(this, MusicService::class.java)
        stopService(musicServiceIntent)
    }
}
