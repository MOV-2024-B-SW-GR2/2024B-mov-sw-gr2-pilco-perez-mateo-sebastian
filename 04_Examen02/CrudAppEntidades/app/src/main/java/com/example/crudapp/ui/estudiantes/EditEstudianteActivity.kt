package com.example.crudapp.ui.estudiantes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.util.copy
import com.example.crudapp.R
import com.example.crudapp.data.api.EstudianteApi
import com.example.crudapp.data.api.RetrofitClient
import com.example.crudapp.data.models.Estudiante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditEstudianteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_estudiante)

        val estudiante = intent.getParcelableExtra<Estudiante>("estudiante")

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        estudiante?.let {
            etNombre.setText(it.nombre)
            etEdad.setText(it.edad.toString())
            etEmail.setText(it.email)
            etTelefono.setText(it.telefono)
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val edad = etEdad.text.toString().toIntOrNull()
            val email = etEmail.text.toString()
            val telefono = etTelefono.text.toString()

            if (nombre.isEmpty() || edad == null || email.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            estudiante?.let {
                val updatedEstudiante = it.copy(
                    nombre = nombre,
                    edad = edad,
                    email = email,
                    telefono = telefono
                )
                updateEstudiante(updatedEstudiante)
            }
        }
    }

    private fun updateEstudiante(estudiante: Estudiante) {
        val api = RetrofitClient.instance.create(EstudianteApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.updateEstudiante(estudiante.id!!, estudiante)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditEstudianteActivity, "Estudiante actualizado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditEstudianteActivity, "Error al actualizar estudiante", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}