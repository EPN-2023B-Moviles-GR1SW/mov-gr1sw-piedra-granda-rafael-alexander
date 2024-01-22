package com.example.myapplication.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.DialogInterface
import android.content.Intent
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.BaseDatosMemoria
import com.example.myapplication.R
import com.example.myapplication.model.Periodico
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    val callbackContenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    // Logica Negocio
                    val data = result.data
                    mostrarSnackbar(
                        "${data?.getStringExtra("nombreModificado")}"
                    )
                }
            }
        }
    val arreglo = BaseDatosMemoria.arreglo
    var posicionItemSeleccionado = -1
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
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
                val listView = findViewById<ListView>(R.id.lv_list_view_noticias)
                val adaptador = listView.adapter as ArrayAdapter<Periodico>
//                mostrarSnackbar("${posicionItemSeleccionado}")
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

        val listView = findViewById<ListView>(R.id.lv_list_view_noticias)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view
        )
        botonAnadirListView
            .setOnClickListener {
                irActividad(FormNuevoPeriodico::class.java)
//                añadirPeriodico(adaptador)
            }
        registerForContextMenu(listView)
    }

    fun abrirDialogo(adaptador: ArrayAdapter<Periodico>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                // Todo: Se debe eliminar el Periodico
                if (posicionItemSeleccionado != -1 && posicionItemSeleccionado < arreglo.size) {
                    arreglo.removeAt(posicionItemSeleccionado)
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


    fun añadirPeriodico(adaptador: ArrayAdapter<Periodico>) {
        val nombres = arrayOf(
            "El Comercio",
            "El Telégrafo",
            "El Extra",
            "La Hora",
            "Expreso",
            "Ultimas Noticias",
            "Hoy",
            "El Universo",
            "Diario del Norte",
            "El Mercurio"
        )
        arreglo.add(
            Periodico(1, nombres.random(), "14/07/02", ArrayList())
        )
        adaptador.notifyDataSetChanged()
    }


    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.lv_list_view_noticias),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConParametros(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("posicionItemSeleccionado", posicionItemSeleccionado)
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }
}