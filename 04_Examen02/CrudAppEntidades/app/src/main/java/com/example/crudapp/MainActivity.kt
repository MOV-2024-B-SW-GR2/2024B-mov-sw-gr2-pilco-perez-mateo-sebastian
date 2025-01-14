package com.example.crudapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.data.api.CursoApi
import com.example.crudapp.data.api.RetrofitClient
import com.example.crudapp.data.models.Curso
import com.example.crudapp.ui.cursos.AddCursoActivity
import com.example.crudapp.ui.cursos.CursoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cursoAdapter: CursoAdapter
    private val cursos = mutableListOf<Curso>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCursos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cursoAdapter = CursoAdapter(cursos, ::onEditCurso, ::onDeleteCurso)
        recyclerView.adapter = cursoAdapter

        // Configurar botón flotante para agregar un nuevo curso
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
        // Recargar la lista de cursos cuando se vuelve a la actividad
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
                    // Mostrar error al usuario
                    showToast("Error al cargar los cursos")
                }
            }
        }
    }

    private fun onEditCurso(curso: Curso) {
        val intent = Intent(this, AddCursoActivity::class.java)
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
                        showToast("Curso eliminado exitosamente")
                    } else {
                        showToast("Error al eliminar el curso")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    showToast("Error al eliminar el curso")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
