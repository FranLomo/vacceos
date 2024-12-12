package com.example.juego

import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

class SwipeGestureListener( private val activity:  MazeActivity) : GestureDetector.SimpleOnGestureListener() {
    private val SWIPETHRESHOLD = 100     // Distancia mínima para considerar un swipe
    private val SWIPEVELOCITYTHRESHOLD = 100  // Velocidad mínima para considerar un swipe

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        try {
            val diffX = e2?.x?.minus(e1?.x ?: 0f) ?: 0f
            val diffY = e2?.y?.minus(e1?.y ?: 0f) ?: 0f

            if (abs(diffX) > abs(diffY)) {
                // Swipe horizontal
                if (abs(diffX) > SWIPETHRESHOLD && abs(velocityX) > SWIPEVELOCITYTHRESHOLD) {
                    if (diffX > 0) {
                        // Swipe a la derecha
                        onSwipeRight()
                    } else {
                        // Swipe a la izquierda
                        onSwipeLeft()
                    }
                    return true
                }
            } else {
                // Swipe vertical
                if (abs(diffY) > SWIPETHRESHOLD && abs(velocityY) > SWIPEVELOCITYTHRESHOLD) {
                    if (diffY > 0) {
                        // Swipe hacia abajo
                        onSwipeDown()
                    } else {
                        // Swipe hacia arriba
                        onSwipeUp()
                    }
                    return true
                }
            }

        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return false
    }

    fun onSwipeRight() {
        // Acción para el swipe a la derecha
        activity.moveRight()
    }

    fun onSwipeLeft() {
        // Acción para el swipe a la izquierda
        activity.moveLeft()
    }

    fun onSwipeUp() {
        // Acción para el swipe hacia arriba
        activity.moveUp()
    }

    fun onSwipeDown() {
        // Acción para el swipe hacia abajo
        activity.moveDown()
    }
}
