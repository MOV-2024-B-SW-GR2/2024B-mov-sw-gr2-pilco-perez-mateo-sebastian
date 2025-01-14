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

class EditCursoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_curso)

        val curso = intent.getParcelableExtra<Curso>("curso")

        val etNombre = findViewById<EditText>(R.id.etNombreCurso)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcionCurso)
        val etDuracion = findViewById<EditText>(R.id.etDuracionCurso)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarCurso)

        // Rellenar campos con los datos del curso
        curso?.let {
            etNombre.setText(it.nombre)
            etDescripcion.setText(it.descripcion)
            etDuracion.setText(it.duracion.toString())
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val duracion = etDuracion.text.toString().toIntOrNull()

            if (nombre.isEmpty() || descripcion.isEmpty() || duracion == null) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            curso?.let {
                val updatedCurso = it.copy(
                    nombre = nombre,
                    descripcion = descripcion,
                    duracion = duracion
                )
                updateCurso(updatedCurso)
            }
        }
    }

    private fun updateCurso(curso: Curso) {
        val api = RetrofitClient.instance.create(CursoApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.updateCurso(curso.id!!, curso)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditCursoActivity, "Curso actualizado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditCursoActivity, "Error al actualizar curso", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
