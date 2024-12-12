package com.example.juego.viewModels

import android.widget.EditText
import android.widget.GridLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.juego.R

class CWViewModel : ViewModel() {

    val isCWSolved = MutableLiveData<Boolean>()
    val cwArray = MutableLiveData<List<List<Char>>>()
    val posPalabras= MutableLiveData<List<Pair<Int, Int>>>()

    private val correctosNumerados = arrayOf(
        R.drawable.correcto1,
        R.drawable.correcto2,
        R.drawable.correcto3,
        R.drawable.correcto4,
        R.drawable.correcto5,
        R.drawable.correcto6,
    )

    var horizontales1 : String
    var horizontales2 : String
    var horizontales3 : String
    var verticales1 : String
    var verticales2 : String
    var verticales3 : String

    init {
        isCWSolved.value= false

        cwArray.value= listOf(
            listOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
            listOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
            listOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', 'M', ' ', ' ', ' ', ' '),
            listOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '),
            listOf(' ', ' ', ' ', 'H', 'I', 'E', 'R', 'R', 'O', ' ', ' ', ' '),
            listOf(' ', 'P', ' ', ' ', ' ', 'S', ' ', 'A', ' ', ' ', ' ', ' '),
            listOf(' ', 'I', ' ', ' ', ' ', 'P', ' ', 'L', ' ', ' ', ' ', ' '),
            listOf(' ', 'N', ' ', ' ', ' ', 'A', ' ', 'L', ' ', ' ', ' ', ' '),
            listOf(' ', 'T', ' ', ' ', ' ', 'D', ' ', 'A', 'D', 'O', 'B', 'E'),
            listOf('F', 'I', 'B', 'U', 'L', 'A', ' ', ' ', ' ', ' ', ' ', ' '),
            listOf(' ', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
            listOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
        )

        posPalabras.value= listOf(Pair(9,0), Pair(4,3), Pair(8,7), Pair(5,1), Pair(4,5), Pair(2,7))

        horizontales1 = "1- Objeto de metal que servía para sujetar las vestimentas."
        horizontales2 = "2- Metal muy usado por los vacceos."
        horizontales3 = "3- Material de construcción de las casas vacceas."
        verticales1= "4- Nombre del yacimiento."
        verticales2= "5- Arma de doble filo hecha de hierro."
        verticales3= "6- Estructura defensiva compuesta de un muro y uno o varios fosos."
    }

    fun setCWPorRol(rol : String) {
        when(rol) {
            "guerrero" -> {
                cwArray.value = listOf(
                    listOf('E','S','P','A','D','A',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ','R',' ',' ',' ',' ',' ','C',' ',' '),
                    listOf(' ',' ',' ','M','U','R','A','L','L','A',' ',' '),
                    listOf(' ',' ',' ','A',' ',' ',' ',' ',' ','B',' ',' '),
                    listOf(' ',' ',' ','D',' ',' ',' ',' ',' ','A',' ',' '),
                    listOf(' ',' ','B','U','I','T','R','E',' ','L',' ',' '),
                    listOf(' ',' ',' ','R',' ',' ',' ','S',' ','L',' ',' '),
                    listOf(' ',' ',' ','A',' ',' ',' ','C',' ','O',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ','U',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ','D',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ','O',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ')
                )

                posPalabras.value = listOf(Pair(0,0), Pair(5,2), Pair(2,3), Pair(0,3), Pair(5,7), Pair(1,9))
                horizontales1 = "1. Arma de doble filo hecha de hierro."
                horizontales2 = "2. Animal al que ofrecían los cuerpos de los guerreros caídos."
                horizontales3 = "3. Estructura defensiva compuesta de un muro y uno o varios fosos."
                verticales1 = "4. Protección de metal para el cuerpo."
                verticales2 = "5. Objeto circular usado para la defensa."
                verticales3 = "6. Montura que usaban algunos guerreros."

            }

            "campesino" -> {
                cwArray.value= listOf(
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ','S',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ','V','I','D',' ',' ',' '),
                    listOf(' ',' ',' ',' ','C',' ',' ','L',' ',' ',' ',' '),
                    listOf(' ',' ',' ','C','E','S','T','O',' ',' ',' ',' '),
                    listOf(' ','H',' ',' ','R',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ','O',' ',' ','E',' ',' ',' ',' ',' ',' ',' '),
                    listOf('A','Z','A','D','A',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ')
                )

                posPalabras.value = listOf(Pair(8,0), Pair(5,3), Pair(3,6), Pair(6,1), Pair(4,4), Pair(2,7))
                horizontales1 = "1. Herramienta para cavar y remover la tierra."
                horizontales2 = "2. Contenedor hecho de fibras vegetales para transportar alimentos."
                horizontales3 = "3. Planta de la que se obtienen las uvas para hacer vino."
                verticales1 = "4. Herramienta de mano de hoja curva para segar."
                verticales2 = "5. Tipo de cultivo más usado por los vacceos."
                verticales3 = "6. Estructura subterránea para almacenar grano."
            }

            "consejo" -> {
                cwArray.value= listOf(
                    listOf(' ',' ',' ',' ','C',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ','O',' ',' ','C',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ','N',' ',' ','E',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ','S',' ',' ','T','O','T','E','M'),
                    listOf(' ','C',' ',' ','E',' ',' ','R',' ',' ',' ',' '),
                    listOf('S','O','N','A','J','E','R','O',' ',' ',' ',' '),
                    listOf(' ','L',' ',' ','O',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ','L',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
                    listOf('C','A','L','I','Z',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ')
                )

                posPalabras.value = listOf(Pair(5,0), Pair(8,0), Pair(3,7), Pair(4,1), Pair(0,4), Pair(1,7))
                horizontales1 = "1. Cajas con canicas en su interior que sonaban al agitarlas."
                horizontales2 = "2. Recipiente con forma de copa usado en ceremonias."
                horizontales3 = "3. Objeto o figura simbólica."
                verticales1 = "4. Adorno de metal o cerámica que se ponía en el cuello."
                verticales2 = "5. Grupo de personas que se reunían para tomar decisiones."
                verticales3 = "6. Símbolo de poder con forma de vara."
            }

            "artesano" -> {
                cwArray.value = listOf(
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','M',' '),
                    listOf(' ',' ',' ',' ',' ',' ','A','G','U','J','A',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','T',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','I',' '),
                    listOf(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','L',' '),
                    listOf(' ',' ',' ',' ','C',' ',' ','F',' ',' ','L',' '),
                    listOf(' ',' ',' ',' ','I',' ','H','O','R','N','O',' '),
                    listOf(' ',' ',' ',' ','N',' ',' ','R',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ','C',' ',' ','J',' ',' ',' ',' '),
                    listOf(' ',' ','S','I','E','R','R','A',' ',' ',' ',' '),
                    listOf(' ',' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ')
                )

                posPalabras.value = listOf(Pair(10,2), Pair(1,6), Pair (7,6), Pair(6,4), Pair(7,7), Pair(0,10))
                horizontales1 = "1. Utensilio para cortar usado por carpinteros."
                horizontales2 = "2. Herramienta usada por los artesanos textiles para coser."
                horizontales3 = "3. Estructura para cocer cerámica."
                verticales1 = "4. Herramienta para tallar."
                verticales2 = "5. Lugar donde se trabaja el metal."
                verticales3 = "6. Objeto de metal usado por herreros entre otros para golpear."
            }
        }
    }

    fun checkIfSolved(grid : GridLayout, totalLetras : Int) {
        var contador= 0
        for(i in 0 until cwArray.value!!.size) {
            for(j in 0 until cwArray.value!![0].size) {
                if(cwArray.value!![i][j]!=' ') {
                    val casilla = grid.getChildAt(i*12+j) as EditText
                    if(casilla.text.contentEquals(cwArray.value!![i][j].toString())) {
                        var eraPrincipio= false
                        for(k in 0 until 6) {
                            if(i==posPalabras.value!![k].first && j==posPalabras.value!![k].second) {
                                casilla.setBackgroundResource(correctosNumerados[k]) // Borde con numerito
                                eraPrincipio=true
                            }
                        }
                        if(!eraPrincipio) {
                            casilla.setBackgroundResource(R.drawable.correcto) // Para que tengan borde
                        }
                        contador++
                    }
                }
            }
        }
        if(contador==totalLetras) {
            isCWSolved.value= true
        }
    }
}