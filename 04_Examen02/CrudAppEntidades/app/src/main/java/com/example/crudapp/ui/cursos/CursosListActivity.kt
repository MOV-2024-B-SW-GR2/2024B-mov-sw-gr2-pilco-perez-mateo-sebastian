package com.example.crudapp.ui.cursos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.data.api.CursoApi
import com.example.crudapp.data.api.RetrofitClient
import com.example.crudapp.data.models.Curso
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CursosListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cursoAdapter: CursoAdapter
    private val cursos = mutableListOf<Curso>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos_list)

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCursos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cursoAdapter = CursoAdapter(cursos, ::onEditCurso, ::onDeleteCurso)
        recyclerView.adapter = cursoAdapter

        // Botón flotante para agregar un nuevo curso
        val fabAddCurso = findViewById<FloatingActionButton>(R.id.fabAddCurso)
        fabAddCurso.setOnClickListener {
            val intent = Intent(this, AddCursoActivity::class.java)
            startActivity(intent)
        }

        // Cargar cursos desde el backend
        loadCursos()
    }

    override fun onResume() {
        super.onResume()
        // Recargar la lista de cursos al volver a la actividad
        loadCursos()
    }

    private fun loadCursos() {
        val api = RetrofitClient.instance.create(CursoApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getCursos()
                withContext(Dispatchers.Main) {
                    cursos.clear()
                    cursos.addAll(response)
                    cursoAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CursosListActivity, "Error al cargar cursos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onEditCurso(curso: Curso) {
        val intent = Intent(this, EditCursoActivity::class.java)
        intent.putExtra("curso", curso)
        startActivity(intent)
    }

    private fun onDeleteCurso(curso: Curso) {
        val api = RetrofitClient.instance.create(CursoApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.deleteCurso(curso.id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        cursos.remove(curso)
                        cursoAdapter.notifyDataSetChanged()
                        Toast.makeText(this@CursosListActivity, "Curso eliminado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@CursosListActivity, "Error al eliminar el curso", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CursosListActivity, "Error al eliminar el curso", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
