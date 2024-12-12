package com.example.juego

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        var isPlayingMusic = false
    }

    override fun onCreate() {
        super.onCreate()
        // Inicializar el MediaPlayer con la música
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        mediaPlayer.isLooping = true // Reproducir en bucle
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Iniciar la reproducción de música
        mediaPlayer.start()
        isPlayingMusic= true
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener y liberar el MediaPlayer
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        isPlayingMusic= false
        mediaPlayer.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
