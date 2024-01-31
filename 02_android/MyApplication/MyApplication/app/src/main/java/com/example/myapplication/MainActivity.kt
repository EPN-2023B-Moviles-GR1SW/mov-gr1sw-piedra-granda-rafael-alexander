package com.example.myapplication

import PeriodicoDAO
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Periodico
import com.example.myapplication.ui.activity.FormNuevoPeriodico
import com.example.myapplication.ui.activity.ListViewNoticia
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val periodicoDAO: PeriodicoDAO by lazy { PeriodicoDAO(this) }
    private lateinit var adaptador: ArrayAdapter<Periodico>

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_periodico, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                mostrarSnackbar("${posicionItemSeleccionado}")
                return true
            }

            R.id.mi_eliminar -> {
                val listView = findViewById<ListView>(R.id.lv_list_view_periodico)
                abrirDialogo(adaptador)
                return true
            }

            R.id.mi_ver -> {
                irActividadConParametros(ListViewNoticia::class.java)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lv_list_view_periodico)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            periodicoDAO.obtenerPeriodicos()
        )
        listView.adapter = adaptador

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView.setOnClickListener {
            irActividad(FormNuevoPeriodico::class.java)
        }

        registerForContextMenu(listView)
    }

    private fun abrirDialogo(adaptador: ArrayAdapter<Periodico>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton("Aceptar") { _, _ ->
            if (posicionItemSeleccionado != -1 && posicionItemSeleccionado < adaptador.count) {
                val periodico = adaptador.getItem(posicionItemSeleccionado)
                periodico?.let {
                    periodicoDAO.eliminarPeriodico(it.id)
                    adaptador.remove(it)
                    adaptador.notifyDataSetChanged()
                    mostrarSnackbar("Eliminar aceptado")
                }
            }
        }
        builder.setNegativeButton("Cancelar", null)

        val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
        builder.setMultiChoiceItems(opciones, null) { _, which, isChecked ->
            mostrarSnackbar("Dio clic en el item $which")
        }

        val dialogo = builder.create()
        dialogo.show()
    }

    private fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(findViewById(R.id.lv_list_view_periodico), texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun irActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("posicionItemSeleccionado", posicionItemSeleccionado)
        startActivity(intentExplicito)
    }
}
