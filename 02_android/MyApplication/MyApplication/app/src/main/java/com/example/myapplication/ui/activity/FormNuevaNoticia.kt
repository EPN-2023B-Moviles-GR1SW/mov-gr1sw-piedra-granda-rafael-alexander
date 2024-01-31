package com.example.myapplication.ui.activity

import NoticiaDAO
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.model.Noticia
import com.google.android.material.snackbar.Snackbar

class FormNuevaNoticia : AppCompatActivity() {

    private val noticiaDAO: NoticiaDAO by lazy { NoticiaDAO(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_noticias)

        val indice = intent.getIntExtra("posicionItemSeleccionado", 0)

        val btnCrearNoticia = findViewById<Button>(R.id.btn_crear_periodico)
        btnCrearNoticia.setOnClickListener {
            val idNoticia = findViewById<EditText>(R.id.input_id).text.toString().toInt()
            val titulo = findViewById<EditText>(R.id.input_nombre).text.toString()
            val autor = findViewById<EditText>(R.id.input_autor).text.toString()
            val fechaPublicacion = findViewById<EditText>(R.id.input_fecha).text.toString()

            val nuevaNoticia = Noticia(idNoticia, titulo, autor, fechaPublicacion)

            noticiaDAO.insertarNoticia(nuevaNoticia, indice)
            mostrarSnackbar("Noticia creada")
            irActividad(ListViewNoticia::class.java)
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(findViewById(R.id.cl_periodico), texto, Snackbar.LENGTH_LONG).show()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
