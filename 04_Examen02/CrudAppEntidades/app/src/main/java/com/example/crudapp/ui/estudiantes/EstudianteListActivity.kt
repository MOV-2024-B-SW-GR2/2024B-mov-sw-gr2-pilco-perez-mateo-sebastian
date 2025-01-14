package com.example.crudapp.ui.estudiantes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.data.api.EstudianteApi
import com.example.crudapp.data.api.RetrofitClient
import com.example.crudapp.data.models.Estudiante
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EstudiantesListActivity : AppCompatActivity() {

    private lateinit var adapter: EstudianteAdapter
    private val estudiantes = mutableListOf<Estudiante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEstudiantes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = EstudianteAdapter(estudiantes, onEditClick = { estudiante ->
            val intent = Intent(this, EditEstudianteActivity::class.java)
            intent.putExtra("estudiante", estudiante)
            startActivity(intent)
        }, onDeleteClick = { estudiante ->
            deleteEstudiante(estudiante)
        })

        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAddEstudiante).setOnClickListener {
            val intent = Intent(this, AddEstudianteActivity::class.java)
            startActivity(intent)
        }

        loadEstudiantes()
    }

    private fun loadEstudiantes() {
        val api = RetrofitClient.instance.create(EstudianteApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getEstudiantes()
            withContext(Dispatchers.Main) {
                estudiantes.clear()
                estudiantes.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun deleteEstudiante(estudiante: Estudiante) {
        val api = RetrofitClient.instance.create(EstudianteApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            api.deleteEstudiante(estudiante.id!!)
            withContext(Dispatchers.Main) {
                estudiantes.remove(estudiante)
                adapter.notifyDataSetChanged()
            }
        }
    }
}