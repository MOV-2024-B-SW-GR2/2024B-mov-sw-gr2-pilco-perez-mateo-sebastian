package com.example.crudapp.ui.cursos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudapp.R
import com.example.crudapp.data.api.CursoApi
import com.example.crudapp.data.api.RetrofitClient
import com.example.crudapp.data.models.Curso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCursoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_curso)

        val etNombre = findViewById<EditText>(R.id.etNombreCurso)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcionCurso)
        val etDuracion = findViewById<EditText>(R.id.etDuracionCurso)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarCurso)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val duracion = etDuracion.text.toString().toIntOrNull()

            if (nombre.isEmpty() || descripcion.isEmpty() || duracion == null) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoCurso = Curso(
                nombre = nombre,
                descripcion = descripcion,
                duracion = duracion
            )

            guardarCurso(nuevoCurso)
        }
    }

    private fun guardarCurso(curso: Curso) {
        val api = RetrofitClient.instance.create(CursoApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createCurso(curso)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddCursoActivity, "Curso guardado exitosamente", Toast.LENGTH_SHORT).show()
                        finish() // Cierra la actividad y regresa a la lista de cursos
                    } else {
                        Toast.makeText(this@AddCursoActivity, "Error al guardar el curso: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddCursoActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
