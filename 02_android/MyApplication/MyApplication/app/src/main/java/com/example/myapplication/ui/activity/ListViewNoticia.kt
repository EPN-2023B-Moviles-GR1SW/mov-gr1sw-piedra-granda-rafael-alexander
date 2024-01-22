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
import com.example.myapplication.BaseDatosMemoria
import com.example.myapplication.R
import com.example.myapplication.model.Noticia
import com.example.myapplication.model.Periodico
import com.google.android.material.snackbar.Snackbar

class ListViewNoticia : AppCompatActivity() {

    var posicionItemSeleccionado = -1
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_noticias, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val indice = intent.getIntExtra("posicionItemSeleccionado", 0)
        val arreglo = BaseDatosMemoria.obtenerNoticias(indice)
        return when (item.itemId) {
            R.id.mi_editar -> {
                mostrarSnackbar("${posicionItemSeleccionado}")
                return true
            }

            R.id.mi_eliminar -> {
                val listView = findViewById<ListView>(R.id.lv_list_view_periodico)
                val adaptador = listView.adapter as ArrayAdapter<Periodico>
                abrirDialogo(adaptador, arreglo)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val indice = intent.getIntExtra("posicionItemSeleccionado",0)
        val arreglo = BaseDatosMemoria.obtenerNoticias(indice)
        setContentView(R.layout.activity_list_view_noticias)
        val listView = findViewById<ListView>(R.id.lv_list_view_periodico)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view_noticias
        )
        botonAnadirListView
            .setOnClickListener {
                mostrarSnackbar("funciona")
                irActividadConParametros(FormNuevaNoticia::class.java)
            }
        registerForContextMenu(listView)
    }

    fun abrirDialogo(adaptador: ArrayAdapter<Periodico>, arreglo: ArrayList<Noticia>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                if (posicionItemSeleccionado != -1 && posicionItemSeleccionado < arreglo.size) {
                    val indicePeriodicoEliminar = intent.getIntExtra("posicionItemSeleccionado", 0)
                    BaseDatosMemoria.eliminarNoticia(posicionItemSeleccionado,indicePeriodicoEliminar)
//                    arreglo.removeAt(posicionItemSeleccionado)
                    adaptador.notifyDataSetChanged()
                    mostrarSnackbar("Eliminar aceptado")
                }
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true, // Lunes seleccionado
            false, // Martes NO seleccionado
            false // Miercoles NO seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            { dialog,
              which,
              isChecked ->
                mostrarSnackbar("Dio clic en el item ${which}")
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.lv_list_view_periodico),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConParametros(clase: Class<*>) {
        val indice = intent.getIntExtra("posicionItemSeleccionado", 0)
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("posicionItemSeleccionado", indice)
        startActivity(intentExplicito)
    }

}
