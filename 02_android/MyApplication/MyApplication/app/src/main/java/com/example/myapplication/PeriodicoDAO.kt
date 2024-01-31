import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.SQLiteConexion
import com.example.myapplication.model.Periodico
import com.example.myapplication.model.Noticia

class PeriodicoDAO(context: Context) {

    private val db: SQLiteDatabase = SQLiteConexion(context).writableDatabase

    fun insertarPeriodico(periodico: Periodico) {
        val values = ContentValues().apply {
            put(SQLiteConexion.COLUMNA_NOMBRE_PERIODICO, periodico.nombre)
            put(SQLiteConexion.COLUMNA_FECHA_PUBLICACION, periodico.fechaPublicacion)
        }
        val idPeriodico = db.insert(SQLiteConexion.TABLA_PERIODICOS, null, values)

        // Insertar noticias asociadas al periodico
        for (noticia in periodico.noticias) {
            insertarNoticia(idPeriodico.toInt(), noticia)
        }
    }

    private fun insertarNoticia(idPeriodico: Int, noticia: Noticia) {
        val values = ContentValues().apply {
            put(SQLiteConexion.COLUMNA_TITULO_NOTICIA, noticia.titulo)
            put(SQLiteConexion.COLUMNA_AUTOR_NOTICIA, noticia.autor)
            put(SQLiteConexion.COLUMNA_FECHA_PUBLICACION_NOTICIA, noticia.fechaPublicacion)
            put(SQLiteConexion.COLUMNA_ID_PERIODICO_FK, idPeriodico)
        }
        db.insert(SQLiteConexion.TABLA_NOTICIAS, null, values)
    }

    @SuppressLint("Range")
    fun obtenerPeriodicos(): List<Periodico> {
        val listaPeriodicos = mutableListOf<Periodico>()
        val cursor: Cursor = db.query(
            SQLiteConexion.TABLA_PERIODICOS,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val idPeriodico = cursor.getInt(cursor.getColumnIndex(SQLiteConexion.COLUMNA_ID_PERIODICO))
            val nombrePeriodico =
                cursor.getString(cursor.getColumnIndex(SQLiteConexion.COLUMNA_NOMBRE_PERIODICO))
            val fechaPublicacion =
                cursor.getString(cursor.getColumnIndex(SQLiteConexion.COLUMNA_FECHA_PUBLICACION))

            // Obtener noticias asociadas al periodico
            val noticias = obtenerNoticiasPorPeriodico(idPeriodico)

            val periodico = Periodico(idPeriodico, nombrePeriodico, fechaPublicacion, noticias)
            listaPeriodicos.add(periodico)
        }
        cursor.close()
        return listaPeriodicos
    }

    private fun obtenerNoticiasPorPeriodico(idPeriodico: Int): ArrayList<Noticia> {
        val listaNoticias = ArrayList<Noticia>()
        val cursor: Cursor = db.query(
            SQLiteConexion.TABLA_NOTICIAS,
            null,
            "${SQLiteConexion.COLUMNA_ID_PERIODICO_FK} = ?",
            arrayOf(idPeriodico.toString()),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val idNoticia = cursor.getInt(cursor.getColumnIndex(SQLiteConexion.COLUMNA_ID_NOTICIA))
            val tituloNoticia =
                cursor.getString(cursor.getColumnIndex(SQLiteConexion.COLUMNA_TITULO_NOTICIA))
            val autorNoticia =
                cursor.getString(cursor.getColumnIndex(SQLiteConexion.COLUMNA_AUTOR_NOTICIA))
            val fechaPublicacionNoticia =
                cursor.getString(cursor.getColumnIndex(SQLiteConexion.COLUMNA_FECHA_PUBLICACION_NOTICIA))

            val noticia = Noticia(idNoticia, tituloNoticia, autorNoticia, fechaPublicacionNoticia)
            listaNoticias.add(noticia)
        }
        cursor.close()
        return listaNoticias
    }

    fun actualizarPeriodico(periodico: Periodico) {
        // Actualizar datos del periodico
        val values = ContentValues().apply {
            put(SQLiteConexion.COLUMNA_NOMBRE_PERIODICO, periodico.nombre)
            put(SQLiteConexion.COLUMNA_FECHA_PUBLICACION, periodico.fechaPublicacion)
        }
        db.update(
            SQLiteConexion.TABLA_PERIODICOS,
            values,
            "${SQLiteConexion.COLUMNA_ID_PERIODICO} = ?",
            arrayOf(periodico.id.toString())
        )

        // Eliminar noticias asociadas al periodico
        eliminarNoticiasPorPeriodico(periodico.id)

        // Insertar noticias actualizadas
        for (noticia in periodico.noticias) {
            insertarNoticia(periodico.id, noticia)
        }
    }

    private fun eliminarNoticiasPorPeriodico(idPeriodico: Int) {
        db.delete(
            SQLiteConexion.TABLA_NOTICIAS,
            "${SQLiteConexion.COLUMNA_ID_PERIODICO_FK} = ?",
            arrayOf(idPeriodico.toString())
        )
    }

    fun eliminarPeriodico(idPeriodico: Int) {
        // Eliminar noticias asociadas al periodico
        eliminarNoticiasPorPeriodico(idPeriodico)

        // Eliminar el periodico
        db.delete(
            SQLiteConexion.TABLA_PERIODICOS,
            "${SQLiteConexion.COLUMNA_ID_PERIODICO} = ?",
            arrayOf(idPeriodico.toString())
        )
    }

}
