package com.example.myapplication.ui.activity

import android.content.DialogInterface
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
import com.example.myapplication.R
import com.example.myapplication.model.Noticia
import com.example.myapplication.model.Periodico
import com.google.android.material.snackbar.Snackbar
import NoticiaDAO

class ListViewNoticia : AppCompatActivity() {

    private val noticiaDAO: NoticiaDAO by lazy { NoticiaDAO(this) }
    private lateinit var adaptador: ArrayAdapter<Noticia>
    private var idPeriodico: Int = 0

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_noticias, menu)
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
                abrirDialogo(adaptador)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_noticias)

        idPeriodico = intent.getIntExtra("posicionItemSeleccionado", 0)
        val arreglo = noticiaDAO.obtenerNoticias()

        val listView = findViewById<ListView>(R.id.lv_list_view_periodico)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view_noticias)
        botonAnadirListView.setOnClickListener {
            mostrarSnackbar("funciona")
            irActividadConParametros(FormNuevaNoticia::class.java)
        }

        registerForContextMenu(listView)
    }

    private fun abrirDialogo(adaptador: ArrayAdapter<Noticia>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton("Aceptar") { _, _ ->
            if (posicionItemSeleccionado != -1 && posicionItemSeleccionado < adaptador.count) {
                val noticia = adaptador.getItem(posicionItemSeleccionado)
                noticia?.let {
                    noticiaDAO.eliminarNoticia(it.id)
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
        intentExplicito.putExtra("posicionItemSeleccionado", idPeriodico)
        startActivity(intentExplicito)
    }
}
