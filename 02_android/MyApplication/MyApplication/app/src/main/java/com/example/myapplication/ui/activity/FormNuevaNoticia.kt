package com.example.myapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.BaseDatosMemoria
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.model.Noticia
import com.google.android.material.snackbar.Snackbar

class FormNuevaNoticia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_noticias)
        val indice = intent.getIntExtra("posicionItemSeleccionado", 0)
        val btnCrearPeriodico = findViewById<Button>(R.id.btn_crear_periodico)
        btnCrearPeriodico
            .setOnClickListener {
                val idNoticia = findViewById<EditText>(R.id.input_id)
                val titulo = findViewById<EditText>(R.id.input_nombre)
                val autor = findViewById<EditText>(R.id.input_autor)
                val fechaPublicacion = findViewById<EditText>(R.id.input_fecha)

                BaseDatosMemoria.crearNoticia(
                    Noticia(
                        idNoticia.text.toString().toInt(),
                        titulo.text.toString(),
                        autor.text.toString(),
                        fechaPublicacion.text.toString()
                    ), indice
                )
                irActividad(ListViewNoticia::class.java)
            }

    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_periodico), // view
                texto, // texto
                Snackbar.LENGTH_LONG // tiempo
            )
            .show()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}

