package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class FRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecycler_view)
        inicializarRecyclerView()
    }

    private fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_entrenadores)
        val adaptador = FRecyclerViewAdaptadorNombreDescripcion(
            this,
            BBaseDatosMemoria.arregloBEntrenador,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes() {
        TODO("Not yet implemented")
    }
}