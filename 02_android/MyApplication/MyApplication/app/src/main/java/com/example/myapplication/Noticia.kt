package com.example.myapplication

class Noticia(
    id: String,
    titulo: String,
    autor: String,
    fechaPublicacion: String,
    relevancia: Double,
    destacada: Boolean
) {
    val id: String
    val titulo: String
    val autor: String
    val fechaPublicacion: String
    val relevancia: Double
    val destacada: Boolean

    init {
        this.id = id
        this.titulo = titulo
        this.autor = autor
        this.fechaPublicacion = fechaPublicacion
        this.relevancia = relevancia
        this.destacada = destacada
    }

}