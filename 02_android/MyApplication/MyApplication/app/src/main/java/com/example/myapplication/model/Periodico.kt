package com.example.myapplication.model

class Periodico(
    id: Int,
    nombre: String,
    fechaFundacion: String,
    noticias: ArrayList<Noticia>
) {
    val id: Int
    val nombre: String
    val fechaPublicacion: String
    val noticias: ArrayList<Noticia>

    init {
        this.id = id
        this.nombre = nombre
        this.fechaPublicacion = fechaFundacion
        this.noticias = noticias
    }

    override fun toString(): String {
        return "$nombre"
    }

    fun obtenerTodasLasNoticias(): ArrayList<Noticia> {
        return noticias
    }

    fun agregarNoticia(noticia: Noticia) {
        noticias.add(noticia)
    }


}
