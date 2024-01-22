package com.example.myapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.BaseDatosMemoria
import com.example.myapplication.R
import com.example.myapplication.model.Periodico
import com.google.android.material.snackbar.Snackbar

class CrudPeriodico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_entrenador)

        val btnCrearPeriodico = findViewById<Button>(R.id.btn_crear_periodico)
        btnCrearPeriodico
            .setOnClickListener {
                val idPeriodico = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val fechaFundacion = findViewById<EditText>(R.id.input_fecha)
                BaseDatosMemoria.arreglo.add(
                    Periodico(
                        idPeriodico.text.toString().toInt(),
                        nombre.text.toString(),
                        fechaFundacion.text.toString(),
                        ArrayList()
                    )
                )
                irActividad(MainActivity::class.java)
            }

    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_sqlite), // view
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

