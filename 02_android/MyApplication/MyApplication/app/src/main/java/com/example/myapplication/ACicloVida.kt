package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
class ACicloVida : AppCompatActivity() {

    var textoGlobal = ""
    fun mostrarSnackBar(texto:String){
        textoGlobal = textoGlobal + " " + texto
        Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostrarSnackBar("OnCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackBar("onStart")
    }


    override fun onResume() {
        super.onResume()
        mostrarSnackBar("onResume")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackBar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackBar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackBar("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackBar("onRestart")
    }



}