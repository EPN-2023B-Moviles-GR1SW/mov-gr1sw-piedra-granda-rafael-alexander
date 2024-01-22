import java.util.Date

class Periodico(
    id:String,
    nombre: String,
    esDigital: Boolean,
    fechaFundacion: String,
    confianza: Double,
    circulación: Int,
    noticias: ArrayList<Noticia>
) {
    val id: String
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
        return "$id,$nombre,$esDigital,$fechaPublicacion,$confianza,$cantidad"
    }


}