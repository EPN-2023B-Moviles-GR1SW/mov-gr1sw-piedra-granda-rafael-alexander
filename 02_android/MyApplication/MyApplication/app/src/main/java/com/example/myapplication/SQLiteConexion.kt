package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.model.Periodico
import com.example.myapplication.model.Noticia

class SQLiteConexion(context: Context) :
    SQLiteOpenHelper(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS) {
    companion object {
        const val NOMBRE_BASE_DATOS = "periodicos.db"
        const val VERSION_BASE_DATOS = 1

        // Tabla de Periodicos
        const val TABLA_PERIODICOS = "periodicos"
        const val COLUMNA_ID_PERIODICO = "id_periodico"
        const val COLUMNA_NOMBRE_PERIODICO = "nombre_periodico"
        const val COLUMNA_FECHA_PUBLICACION = "fecha_publicacion"

        // Tabla de Noticias
        const val TABLA_NOTICIAS = "noticias"
        const val COLUMNA_ID_NOTICIA = "id_noticia"
        const val COLUMNA_TITULO_NOTICIA = "titulo_noticia"
        const val COLUMNA_AUTOR_NOTICIA = "autor_noticia"
        const val COLUMNA_FECHA_PUBLICACION_NOTICIA = "fecha_publicacion_noticia"
        const val COLUMNA_ID_PERIODICO_FK = "id_periodico_fk"

        const val CREAR_TABLA_PERIODICOS =
            "CREATE TABLE $TABLA_PERIODICOS (" +
                    "$COLUMNA_ID_PERIODICO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMNA_NOMBRE_PERIODICO TEXT, " +
                    "$COLUMNA_FECHA_PUBLICACION TEXT)"

        const val CREAR_TABLA_NOTICIAS =
            "CREATE TABLE $TABLA_NOTICIAS (" +
                    "$COLUMNA_ID_NOTICIA INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMNA_TITULO_NOTICIA TEXT, " +
                    "$COLUMNA_AUTOR_NOTICIA TEXT, " +
                    "$COLUMNA_FECHA_PUBLICACION_NOTICIA TEXT, " +
                    "$COLUMNA_ID_PERIODICO_FK INTEGER, " +
                    "FOREIGN KEY($COLUMNA_ID_PERIODICO_FK) REFERENCES $TABLA_PERIODICOS($COLUMNA_ID_PERIODICO));"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREAR_TABLA_PERIODICOS)
        db?.execSQL(CREAR_TABLA_NOTICIAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_PERIODICOS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_NOTICIAS")
        onCreate(db)
    }
}
