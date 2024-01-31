import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.SQLiteConexion
import com.example.myapplication.SQLiteConexion.Companion.COLUMNA_AUTOR_NOTICIA
import com.example.myapplication.SQLiteConexion.Companion.COLUMNA_FECHA_PUBLICACION_NOTICIA
import com.example.myapplication.SQLiteConexion.Companion.COLUMNA_ID_NOTICIA
import com.example.myapplication.SQLiteConexion.Companion.COLUMNA_ID_PERIODICO
import com.example.myapplication.SQLiteConexion.Companion.COLUMNA_TITULO_NOTICIA
import com.example.myapplication.SQLiteConexion.Companion.TABLA_NOTICIAS
import com.example.myapplication.model.Noticia

class NoticiaDAO(context: Context) {

    private val db: SQLiteDatabase = SQLiteConexion(context).writableDatabase

    fun insertarNoticia(noticia: Noticia, indice: Int) {
        val values = ContentValues().apply {
            put(SQLiteConexion.COLUMNA_TITULO_NOTICIA, noticia.titulo)
            put(SQLiteConexion.COLUMNA_AUTOR_NOTICIA, noticia.autor)
            put(SQLiteConexion.COLUMNA_FECHA_PUBLICACION_NOTICIA, noticia.fechaPublicacion)
            // Puedes agregar más columnas si es necesario
        }
        db.insert(SQLiteConexion.TABLA_NOTICIAS, null, values)
    }

    @SuppressLint("Range")
    fun obtenerNoticias(): List<Noticia> {
        val listaNoticias = mutableListOf<Noticia>()
        val cursor: Cursor = db.query(
            SQLiteConexion.TABLA_NOTICIAS,
            null,
            null,
            null,
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

            // Puedes obtener más columnas si es necesario

            val noticia = Noticia(idNoticia, tituloNoticia, autorNoticia, fechaPublicacionNoticia)
            listaNoticias.add(noticia)
        }
        cursor.close()
        return listaNoticias
    }

    fun actualizarNoticia(noticia: Noticia) {
        val values = ContentValues().apply {
            put(SQLiteConexion.COLUMNA_TITULO_NOTICIA, noticia.titulo)
            put(SQLiteConexion.COLUMNA_AUTOR_NOTICIA, noticia.autor)
            put(SQLiteConexion.COLUMNA_FECHA_PUBLICACION_NOTICIA, noticia.fechaPublicacion)
            // Puedes agregar más columnas si es necesario
        }
        db.update(
            SQLiteConexion.TABLA_NOTICIAS,
            values,
            "${SQLiteConexion.COLUMNA_ID_NOTICIA} = ?",
            arrayOf(noticia.id.toString())
        )
    }

    fun eliminarNoticia(idNoticia: Int) {
        db.delete(
            SQLiteConexion.TABLA_NOTICIAS,
            "${SQLiteConexion.COLUMNA_ID_NOTICIA} = ?",
            arrayOf(idNoticia.toString())
        )
    }

    fun obtenerNoticiasPorPeriodico(idPeriodico: Int): List<Noticia> {
        val listaNoticias = mutableListOf<Noticia>()
        val query = "SELECT * FROM $TABLA_NOTICIAS WHERE $COLUMNA_ID_PERIODICO = $idPeriodico"

        val cursor: Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val idNoticia = cursor.getInt(cursor.getColumnIndex(COLUMNA_ID_NOTICIA))
            val tituloNoticia = cursor.getString(cursor.getColumnIndex(COLUMNA_TITULO_NOTICIA))
            val autorNoticia = cursor.getString(cursor.getColumnIndex(COLUMNA_AUTOR_NOTICIA))
            val fechaPublicacionNoticia =
                cursor.getString(cursor.getColumnIndex(COLUMNA_FECHA_PUBLICACION_NOTICIA))

            val noticia = Noticia(idNoticia, tituloNoticia, autorNoticia, fechaPublicacionNoticia)
            listaNoticias.add(noticia)
        }
        cursor.close()
        return listaNoticias
    }


}
