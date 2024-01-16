package com.example.myapplication

class Periodico(
    id: Int,
    nombre: String,
    fechaFundacion: String,
    noticias: ArrayList<Noticia>
) {
    val id: Int
    val nombre: String
    val fechaPublicacion: String

    init {
        this.id = id
        this.nombre = nombre
        this.fechaPublicacion = fechaFundacion
    }

    override fun toString(): String {
        return "$nombre"
    }


}