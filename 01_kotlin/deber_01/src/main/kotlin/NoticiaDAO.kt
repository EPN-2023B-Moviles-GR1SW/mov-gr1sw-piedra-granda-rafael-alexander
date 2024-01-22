import java.io.*

class NoticiaDAO : GenericDAO<Noticia, String> {
    val archivo = File("noticias.txt")

    override fun create(noticia:Noticia) {
        val fileWriter1 = FileWriter(archivo, true)
        val printWriter1 = PrintWriter(fileWriter1)
        printWriter1.println(noticia)
        printWriter1.close()
    }


    override fun getById(id: String) {
        try {
            val fileReader = FileReader(archivo)
            val bufferReader = BufferedReader(fileReader)

            var linea = bufferReader.readLine()
            var coincidencia: String? = null
            while (linea != null) {
                if (linea.contains(id)) {
                    coincidencia = linea
                }
                linea = bufferReader.readLine()
            }
            if (coincidencia != null) {
                println(" Coincidencia: $coincidencia")
            } else {
                println("No se encontraron coincidencias")
            }
            bufferReader.close()
            fileReader.close()

        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

    override fun delete(id: String) {
        val archivoTemp = File("artistas_temp.txt")

        try {
            val fileReader = FileReader(archivo)
            val bufferReader = BufferedReader(fileReader)
            val fileWriter = FileWriter(archivoTemp)
            val printWriter = PrintWriter(fileWriter)

            var linea = bufferReader.readLine()
            var coincidencia: String? = null

            while (linea != null) {
                if (linea.contains(id)) {
                    coincidencia = linea
                    println("Se ha eliminado el artista $linea")
                    printWriter.println("")
                } else {

                    printWriter.println(linea)

                }
                linea = bufferReader.readLine()
            }
            if (coincidencia == null) {
                println("No se ha eliminado nada")
            }

            bufferReader.close()
            printWriter.close()
            fileReader.close()
            fileWriter.close()

            archivo.delete()
            archivoTemp.renameTo(archivo)

        } catch (ex: IOException) {
            ex.printStackTrace()
        }


    }

    override fun update(id: String, noticia: Noticia) {
        val archivoTemp = File("artistas_temp.txt")

        try {
            val fileReader = FileReader(archivo)
            val bufferReader = BufferedReader(fileReader)
            val fileWriter = FileWriter(archivoTemp)
            val printWriter = PrintWriter(fileWriter)

            var linea = bufferReader.readLine()

            while (linea != null) {
                if (linea.contains(id)) {
                    printWriter.println(noticia)
                } else {
                    printWriter.println(linea)
                }
                linea = bufferReader.readLine()
            }

            bufferReader.close()
            printWriter.close()
            fileReader.close()
            fileWriter.close()

            archivo.delete()
            archivoTemp.renameTo(archivo)

        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}