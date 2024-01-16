package com.example.myapplication

class BaseDatosMemoria {
    companion object {
        val arreglo = arrayListOf<Periodico>()

        init {
            arreglo.add(
                Periodico(1, "Periodico1", true, "14/07/02", 2.2, 20, ArrayList())
            )
            arreglo.add(
                Periodico(1, "Periodico2", true, "14/07/02", 2.2, 20, ArrayList())
            )
            arreglo.add(
                Periodico(1, "Periodico3", true, "14/07/02", 2.2, 20, ArrayList())
            )
        }
    }

}