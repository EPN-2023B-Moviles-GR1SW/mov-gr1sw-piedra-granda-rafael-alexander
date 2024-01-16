package com.example.myapplication

class BaseDatosMemoria {
    companion object {
        val arreglo = arrayListOf<Periodico>()

        init {
            arreglo.add(
                Periodico(1, "Periodico1",  "14/07/02",  ArrayList())
            )
            arreglo.add(
                Periodico(1, "Periodico2",  "14/07/02",  ArrayList())
            )
            arreglo.add(
                Periodico(1, "Periodico3",  "14/07/02",  ArrayList())
            )
        }
    }

}