package com.example.myapplication.model

class Noticia(
    id: Int,
    titulo: String,
    autor: String,
    fechaPublicacion: String,
) {
    val id: Int
    val titulo: String
    val autor: String
    val fechaPublicacion: String

    init {
        this.id = id
        this.titulo = titulo
        this.autor = autor
        this.fechaPublicacion = fechaPublicacion

    }

    override fun toString(): String {
        return "$titulo"
    }

}