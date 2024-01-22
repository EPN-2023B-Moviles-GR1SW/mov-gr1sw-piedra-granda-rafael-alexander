package com.example.myapplication

import com.example.myapplication.model.Noticia
import com.example.myapplication.model.Periodico

class BaseDatosMemoria {
    companion object {
        val arreglo = arrayListOf<Periodico>()

        init {
            arreglo.add(
                Periodico(
                    1,
                    "El Comercio",
                    "14/07/02",
                    arrayListOf(
                        Noticia(
                            1,
                            "Descubren que los pingüinos pueden volar y el Ártico se llena de pájaros negros y blancos",
                            "Jose delgado",
                            "14/07/02"
                        ),
                        Noticia(
                            2,
                            "La ONU declara que los unicornios son una especie protegida y mágica",
                            "Jose delgado",
                            "14/07/02"
                        ),
                        Noticia(
                            3,
                            "Investigadores encuentran portal a otra dimensión en el fondo de una taza de café",
                            "Jose delgado",
                            "14/07/02"
                        )
                    )
                )
            )
            arreglo.add(
                Periodico(
                    2,
                    "El Universo",
                    "14/07/02",
                    arrayListOf(
                        Noticia(
                            1,
                            "Nuevo estudio revela que los gatos son expertos en física cuántica",
                            "Jose delgado",
                            "14/07/02"
                        ),
                        Noticia(
                            2,
                            "Aliens invaden la Tierra para intercambiar recetas de cocina con los humanos",
                            "Jose delgado",
                            "14/07/02"
                        ),
                        Noticia(
                            3,
                            "Hormigas bailarinas conquistan el mundo con coreografías impresionantes",
                            "Jose delgado",
                            "14/07/02"
                        )

                    )
                )
            )
            arreglo.add(
                Periodico(
                    3,
                    "La Hora",
                    "14/07/02",
                    arrayListOf(
                        Noticia(
                            1,
                            "Encuesta mundial: el 90% de las personas prefiere vivir en casas de jengibre",
                            "Jose delgado",
                            "14/07/02"
                        ),
                        Noticia(
                            2,
                            "Científicos crean arco iris artificial en laboratorio para alegrar los días lluviosos",
                            "Jose delgado",
                            "14/07/02"
                        ),
                        Noticia(
                            3,
                            "Astronautas encuentran estación de servicio en la Luna para repostar cohetes",
                            "Jose delgado",
                            "14/07/02"
                        )
                    )
                )
            )
        }

        fun obtenerNoticias(indice: Int): ArrayList<Noticia> {
            return arreglo.get(indice).obtenerTodasLasNoticias()
        }

        fun crearNoticia(noticia: Noticia, indice: Int) {
            arreglo.get(indice).agregarNoticia(noticia)
        }

        fun eliminarNoticia(posicionItemSeleccionado: Int, indicePeriodicoEliminar: Int) {
            val periodico = arreglo.get(indicePeriodicoEliminar)
            val noticias = periodico.obtenerTodasLasNoticias()

            if (posicionItemSeleccionado in 0 until noticias.size) {
                noticias.removeAt(posicionItemSeleccionado)
            }
        }
    }


}