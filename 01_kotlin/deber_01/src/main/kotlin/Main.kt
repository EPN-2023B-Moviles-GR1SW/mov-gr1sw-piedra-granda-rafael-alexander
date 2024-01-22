fun main() {
    // Crear instancias de tus objetos (Periodico y Noticia)
    val periodicoDAO = PeriodicoDAO()
    val noticiaDAO = NoticiaDAO()

//    val periodico = Periodico(
//        "1",
//        "El Periodico",
//        true,
//        "2022-01-01",
//        4.5,
//        10000,
//        ArrayList()
//    )
//
//    val noticia = Noticia(
//        "1",
//        "Título de la Noticia",
//        "Autor de la Noticia",
//        "2022-01-02",
//        4.0,
//        true
//    )

    // Caso de prueba para CREATE
//    periodicoDAO.create(periodico)
//    noticiaDAO.create(noticia)

    // Caso de prueba para GET BY ID
//    periodicoDAO.getById("1")
//    noticiaDAO.getById("1")

    // Caso de prueba para UPDATE
    val nuevoPeriodico = Periodico(
        "1",
        "Nuevo Periodico",
        false,
        "2022-01-03",
        4.2,
        12000,
        ArrayList()
    )

    val nuevaNoticia = Noticia(
        "1",
        "Nuevo Título",
        "Nuevo Autor",
        "2022-01-04",
        4.3,
        false
    )
//
//    periodicoDAO.update("1", nuevoPeriodico)
//    noticiaDAO.update("1", nuevaNoticia)

    // Caso de prueba para DELETE
    periodicoDAO.delete("1")
    noticiaDAO.delete("1")
}
