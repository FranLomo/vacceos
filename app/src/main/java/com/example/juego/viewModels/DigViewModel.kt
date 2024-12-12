package com.example.juego.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs

class DigViewModel : ViewModel() {
    val found1= MutableLiveData<Boolean>();
    val found2= MutableLiveData<Boolean>();
    val found3= MutableLiveData<Boolean>();
    val found4= MutableLiveData<Boolean>();
    private var foundParts1= intArrayOf(0, 0, 0)
    private var foundParts2= intArrayOf(0, 0, 0)
    private var foundParts3= intArrayOf(0, 0, 0)
    private var foundParts4= intArrayOf(0, 0, 0)
    private var treasure1Pos1 : Pair<Float, Float>
    private var treasure1Pos2 : Pair<Float, Float>
    private var treasure1Pos3 : Pair<Float, Float>
    private var treasure2Pos1 : Pair<Float, Float>
    private var treasure2Pos2 : Pair<Float, Float>
    private var treasure2Pos3 : Pair<Float, Float>
    private var treasure3Pos1 : Pair<Float, Float>
    private var treasure3Pos2 : Pair<Float, Float>
    private var treasure3Pos3 : Pair<Float, Float>
    private var treasure4Pos1 : Pair<Float, Float>
    private var treasure4Pos2 : Pair<Float, Float>
    private var treasure4Pos3 : Pair<Float, Float>

    var nivel= 0


    init {
        found1.value= false
        found2.value= false
        found3.value= false


        treasure1Pos1 = Pair(85.3f, 20.8f)   //Al hacer la escala se queda en un cuadrado de 100x100
        treasure1Pos2 = Pair(53.4f, 33.8f)
        treasure1Pos3 = Pair(34.5f, 44.44f)

        treasure2Pos1 = Pair(18f, 20.8f)
        treasure2Pos2 = Pair(21.5f, 30.26f)
        treasure2Pos3 = Pair(13.24f, 29.07f)

        treasure3Pos1 = Pair(40.42f, 68.08f)
        treasure3Pos2 = Pair(27.4f, 86.28f)
        treasure3Pos3 = Pair(38f, 90.54f)

        treasure4Pos1 = Pair(78.25f, 68.08f)
        treasure4Pos2 = Pair(68.8f, 82.26f)
        treasure4Pos3 = Pair(80.6f, 81.08f)

    }

    fun checkIfFound(x : Float, y : Float, scaleX : Float, scaleY : Float) {
        val sensibilidad= 22f

        if(abs(x-treasure1Pos1.first*scaleX)<sensibilidad && abs(y-treasure1Pos1.second*scaleY)<sensibilidad) {
            foundParts1[0]= 1
        }

        if(abs(x-treasure1Pos2.first*scaleX)<sensibilidad && abs(y-treasure1Pos2.second*scaleY)<sensibilidad) {
            foundParts1[1]= 1
        }

        if(abs(x-treasure1Pos3.first*scaleX)<sensibilidad && abs(y-treasure1Pos3.second*scaleY)<sensibilidad) {
            foundParts1[2]= 1
        }

        if(!foundParts1.contains(0)) {
            found1.value=true
            foundParts1= intArrayOf(0,0,0)
        }

        if(abs(x-treasure2Pos1.first*scaleX)<sensibilidad && abs(y-treasure2Pos1.second*scaleY)<sensibilidad) {
            foundParts2[0]= 1
        }

        if(abs(x-treasure2Pos2.first*scaleX)<sensibilidad && abs(y-treasure2Pos2.second*scaleY)<sensibilidad) {
            foundParts2[1]= 1
        }

        if(abs(x-treasure2Pos3.first*scaleX)<sensibilidad && abs(y-treasure2Pos3.second*scaleY)<sensibilidad) {
            foundParts2[2]= 1
        }

        if(!foundParts2.contains(0)) {
            found2.value=true
            foundParts2= intArrayOf(0,0,0)
        }

        if(abs(x-treasure3Pos1.first*scaleX)<sensibilidad && abs(y-treasure3Pos1.second*scaleY)<sensibilidad) {
            foundParts3[0]= 1
        }

        if(abs(x-treasure3Pos2.first*scaleX)<sensibilidad && abs(y-treasure3Pos2.second*scaleY)<sensibilidad) {
            foundParts3[1]= 1
        }

        if(abs(x-treasure3Pos3.first*scaleX)<sensibilidad && abs(y-treasure3Pos3.second*scaleY)<sensibilidad) {
            foundParts3[2]= 1
        }

        if(!foundParts3.contains(0)) {
            found3.value=true
            foundParts3= intArrayOf(0,0,0)
        }

        if(abs(x-treasure4Pos1.first*scaleX)<sensibilidad && abs(y-treasure4Pos1.second*scaleY)<sensibilidad) {
            foundParts4[0]= 1
        }

        if(abs(x-treasure4Pos2.first*scaleX)<sensibilidad && abs(y-treasure4Pos2.second*scaleY)<sensibilidad) {
            foundParts4[1]= 1
        }

        if(abs(x-treasure4Pos3.first*scaleX)<sensibilidad && abs(y-treasure4Pos3.second*scaleY)<sensibilidad) {
            foundParts4[2]= 1
        }

        if(!foundParts4.contains(0)) {
            found4.value=true
            foundParts4= intArrayOf(0,0,0)
        }
    }

    fun setNivelDig(nivel : Int) {
        this.nivel= nivel

        when(nivel) {
            0 -> {
                treasure1Pos1 = Pair(85.3f, 20.8f)   //Al hacer la escala se queda en un cuadrado de 100x100
                treasure1Pos2 = Pair(53.4f, 33.8f)
                treasure1Pos3 = Pair(34.5f, 44.44f)

                treasure2Pos1 = Pair(18f, 20.8f)
                treasure2Pos2 = Pair(21.5f, 30.26f)
                treasure2Pos3 = Pair(13.24f, 29.07f)

                treasure3Pos1 = Pair(40.42f, 68.08f)
                treasure3Pos2 = Pair(27.4f, 86.28f)
                treasure3Pos3 = Pair(38f, 90.54f)

                treasure4Pos1 = Pair(78.25f, 68.08f)
                treasure4Pos2 = Pair(68.8f, 82.26f)
                treasure4Pos3 = Pair(80.6f, 81.08f)
            }

            1 -> {
                treasure1Pos1 = Pair(13.51f, 23.59f)
                treasure1Pos2 = Pair(24.32f, 21.44f)
                treasure1Pos3 = Pair(66.21f, 20.37f)

                treasure2Pos1 = Pair(74.32f, 48.52f)
                treasure2Pos2 = Pair(64.86f, 39.14f)
                treasure2Pos3 = Pair(60.81f, 45.84f)

                treasure3Pos1 = Pair(22.87f, 60.58f)
                treasure3Pos2 = Pair(10.81f, 63.27f)
                treasure3Pos3 = Pair(22.97f, 69.97f)

                treasure4Pos1 = Pair(66.21f, 68.63f)
                treasure4Pos2 = Pair(58.10f, 83.37f)
                treasure4Pos3 = Pair(44.59f, 78.01f)
            }

            2 -> {
                treasure1Pos1 = Pair(16.21f, 10.99f)
                treasure1Pos2 = Pair(10.81f, 20.37f)
                treasure1Pos3 = Pair(17.56f, 21.71f)

                treasure2Pos1 = Pair(40.54f, 47.18f)
                treasure2Pos2 = Pair(32.43f, 54.24f)
                treasure2Pos3 = Pair(31.08f, 65.95f)

                treasure3Pos1 = Pair(18.91f, 67.29f)
                treasure3Pos2 = Pair(9.45f, 87.39f)
                treasure3Pos3 = Pair(22.97f, 83.37f)

                treasure4Pos1 = Pair(78.37f, 45.84f)
                treasure4Pos2 = Pair(72.97f, 76.67f)
                treasure4Pos3 = Pair(89.18f, 68.63f)
            }
        }
    }
}