package com.example.juego

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController

object Utils {

    fun dividirImagenPuzzle(context: Context, drawableId: Int, rows : Int, cols : Int): Array<Bitmap?> {
        // Cargar la imagen desde los recursos
        val originalBitmap = BitmapFactory.decodeResource(context.resources, drawableId)
            ?: throw IllegalArgumentException("No se pudo cargar la imagen.")

        val scaledBitMap= Bitmap.createScaledBitmap(originalBitmap, 1080, 1436, true)

        // Obtener las dimensiones de cada celda
        val cellWidth = scaledBitMap.width / cols
        val cellHeight = scaledBitMap.height / rows

        // Crear un array para almacenar las sub-imágenes
        val grid = arrayOfNulls<Bitmap>(rows * cols)

        // Cortar la imagen en partes
        var index = 0
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                // Ajustar el tamaño de la última columna y fila
                val x = col * cellWidth
                val y = row * cellHeight

                val adjustedWidth = if (col == cols - 1) scaledBitMap.width - x else cellWidth
                val adjustedHeight = if (row == rows - 1) scaledBitMap.height - y else cellHeight

                // Crear un nuevo Bitmap con las dimensiones ajustadas
                val subBitmap = Bitmap.createBitmap(scaledBitMap, x, y, adjustedWidth, adjustedHeight)
                grid[index] = subBitmap
                index++
            }
        }

        return grid
    }

    fun pantallaCompleta(window: Window, activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Para Android 11 y versiones superiores
            val windowInsetsController = window.insetsController
            if (windowInsetsController != null) {
                windowInsetsController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                windowInsetsController.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Para versiones anteriores a Android 11
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
        activity.requestedOrientation =  ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
