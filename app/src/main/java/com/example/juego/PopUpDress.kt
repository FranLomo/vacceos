package com.example.juego

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class PopUpDress : DialogFragment() {

    companion object {
        fun newInstance(savedInstanceState: Bundle?, imageList: List<Int>, capa: Int): PopUpDress {
            val popUp = PopUpDress()
            val args = Bundle()
            args.putIntegerArrayList("imageList", ArrayList(imageList))
            args.putInt("capa", capa)
            popUp.arguments = args
            return popUp
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.pop_up_dress, container, false)
        val imageList = arguments?.getIntegerArrayList("imageList")
        val capa = arguments?.getInt("capa")

        view.findViewById<ImageView>(R.id.cerrarBoton).setOnClickListener {
            val dressActivity = activity as? DressUpActivity
            dressActivity?.setImagenCapa(R.drawable.blank, capa ?: 0)
            dismiss()
        }

        val grid = view.findViewById<GridLayout>(R.id.gridDress)
        grid.columnCount = 4
        grid.rowCount = 5

        imageList?.forEach { item ->
            val imageView = ImageView(activity).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 180
                    height = 180
                }
                setImageResource(item)


                scaleType = ImageView.ScaleType.CENTER_CROP

                setOnClickListener {
                    val dressActivity = activity as? DressUpActivity
                    dressActivity?.setImagenCapa(item, capa ?: 0)
                    dismiss()
                }
            }

            grid.addView(imageView)
        }

        return view
    }
}
