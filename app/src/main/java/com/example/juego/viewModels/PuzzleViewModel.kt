package com.example.juego.viewModels

import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.juego.Utils.dividirImagenPuzzle

class PuzzleViewModel : ViewModel() {

    // Lista de piezas en orden aleatorio
    val puzzlePieces = MutableLiveData<List<Int>>()
    val isPuzzleSolved = MutableLiveData<Boolean>()
    private var numPiezas : Int = 11

    init {
        // Inicializa las piezas desordenadas
        puzzlePieces.value = (0..numPiezas).toMutableList().shuffled()
        isPuzzleSolved.value = false
    }

    // Metodo para comprobar si el puzzle está resuelto
    fun checkIfSolved(grid: GridLayout) {
        val currentPieces = mutableListOf<Int>()
        for (i in 0 until grid.childCount) {
            val imageView = grid.getChildAt(i) as ImageView
            val id = imageView.id
            currentPieces.add(id)
        }

        // Comprueba si las piezas están en orden
        isPuzzleSolved.value = (currentPieces == (0 until numPiezas).toList())
    }

    fun setNumPiezas(numPiezas : Int) {
        this.numPiezas= numPiezas
        puzzlePieces.value= (0 until numPiezas).toMutableList().shuffled()
    }
    fun getNumPiezas(): Int {
        return this.numPiezas
    }
}
