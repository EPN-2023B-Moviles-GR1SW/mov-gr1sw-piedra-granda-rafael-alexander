package com.example.myapplication.ui.activity

import PeriodicoDAO
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.model.Periodico
import com.google.android.material.snackbar.Snackbar

class FormNuevoPeriodico : AppCompatActivity() {

    private val periodicoDAO: PeriodicoDAO by lazy { PeriodicoDAO(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_periodico)

        val btnCrearPeriodico = findViewById<Button>(R.id.btn_crear_periodico)
        btnCrearPeriodico.setOnClickListener {
            val idPeriodico = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val fechaFundacion = findViewById<EditText>(R.id.input_fecha)

            val nuevoPeriodico = Periodico(
                idPeriodico.text.toString().toInt(),
                nombre.text.toString(),
                fechaFundacion.text.toString(),
                ArrayList()
            )

            periodicoDAO.insertarPeriodico(nuevoPeriodico)
            mostrarSnackbar("Periodico creado")
            irActividad(MainActivity::class.java)
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_periodico), // view
                texto, // texto
                Snackbar.LENGTH_LONG // tiempo
            )
            .show()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
