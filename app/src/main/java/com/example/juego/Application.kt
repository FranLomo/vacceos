package com.example.juego

import AppLifecycleObserver
import android.app.Application
import android.content.Intent
import java.util.Locale

class Application : Application() {

    private lateinit var appLifecycleObserver: AppLifecycleObserver

    override fun onCreate() {
        super.onCreate()
        appLifecycleObserver = AppLifecycleObserver(this)
        registerActivityLifecycleCallbacks(appLifecycleObserver)
        val musicServiceIntent = Intent(this, MusicService::class.java)
        startService(musicServiceIntent)
    }
}