package com.example.myapplication

class Periodico(
    id: Int,
    nombre: String,
    esDigital: Boolean,
    fechaFundacion: String,
    confianza: Double,
    circulación: Int,
    noticias: ArrayList<Noticia>
) {
    val id: Int
    val nombre: String
    val esDigital: Boolean
    val fechaPublicacion: String
    val confianza: Double
    val cantidad: Int

    init {
        this.id = id
        this.nombre = nombre
        this.esDigital = esDigital
        this.fechaPublicacion = fechaFundacion
        this.confianza = confianza
        this.cantidad = circulación
    }

    override fun toString(): String {
        return "$nombre"
    }


}