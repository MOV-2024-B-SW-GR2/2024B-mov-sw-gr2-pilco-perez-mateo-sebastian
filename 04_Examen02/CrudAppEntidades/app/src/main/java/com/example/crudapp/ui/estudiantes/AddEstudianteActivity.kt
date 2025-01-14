package com.example.crudapp.ui.estudiantes

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.crudapp.R
import com.example.crudapp.data.api.CursoApi
import com.example.crudapp.data.api.EstudianteApi
import com.example.crudapp.data.api.RetrofitClient
import com.example.crudapp.data.models.Curso
import com.example.crudapp.data.models.Estudiante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEstudianteActivity : AppCompatActivity() {

    private val cursos = mutableListOf<Curso>()
    private var selectedCursoId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_estudiante)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val spinnerCursos = findViewById<Spinner>(R.id.spinnerCursos)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Cargar los cursos disponibles desde el backend
        loadCursos(spinnerCursos)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val edad = etEdad.text.toString().toIntOrNull()
            val email = etEmail.text.toString()
            val telefono = etTelefono.text.toString()

            if (nombre.isEmpty() || edad == null || email.isEmpty() || telefono.isEmpty() || selectedCursoId == null) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val estudiante = Estudiante(
                nombre = nombre,
                edad = edad,
                email = email,
                telefono = telefono,
                curso_id = selectedCursoId!! // Asignar el curso seleccionado
            )

            saveEstudiante(estudiante)
        }
    }

    private fun loadCursos(spinner: Spinner) {
        val api = RetrofitClient.instance.create(CursoApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getCursos()
            withContext(Dispatchers.Main) {
                if (response.isNotEmpty()) {
                    cursos.clear()
                    cursos.addAll(response)

                    // Convertir la lista de cursos en una lista de nombres
                    val cursoNombres = cursos.map { it.nombre }

                    // Configurar el spinner con los nombres de los cursos
                    val adapter = ArrayAdapter(this@AddEstudianteActivity, android.R.layout.simple_spinner_item, cursoNombres)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    // Manejar la selección del curso
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            selectedCursoId = cursos[position].id
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            selectedCursoId = null
                        }
                    }
                } else {
                    Toast.makeText(this@AddEstudianteActivity, "No hay cursos disponibles", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveEstudiante(estudiante: Estudiante) {
        val api = RetrofitClient.instance.create(EstudianteApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.createEstudiante(estudiante)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddEstudianteActivity, "Estudiante guardado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AddEstudianteActivity, "Error al guardar estudiante", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
