package org.example.controller

import org.example.entities.Curso
import org.example.entities.Estudiante
import org.example.utils.JsonManager


class controlador {
    private var cursos: MutableList<Curso> = JsonManager.cargarDesdeJson()

    //Crear Curso
    fun agregarCurso(id: Int, name: String, descripcion: String, duracion: Int) {
        cursos.add(Curso(id, name, descripcion, duracion))
        guardarCambios()
    }

    //Leer cursos
    fun listarCurso(): MutableList<Curso> = cursos

    //Eliminar cursos
    fun eliminarCurso(idCurso: Int): Boolean {
        val curso = cursos.find { it.id == idCurso }
        return if (curso != null) {
            cursos.remove(curso)
            guardarCambios()
            true
        } else {
            false
        }
    }

    //Crear estudiante en un curso
    fun agregarEstudianteACurso(idCurso: Int, idEstudiante: Int, nombre: String, edad: Int, email: String, telefono: Int) {
        val curso = cursos.find { it.id == idCurso }
        curso?.estudiantes?.add(Estudiante(idEstudiante, nombre, edad, email, telefono))
        guardarCambios()
    }

    //Leer estudiante de un curso
    fun listarEstudiantesCurso(idCurso: Int): List<Estudiante> {
        val curso = cursos.find { it.id == idCurso }
        return curso?.estudiantes ?: emptyList()
    }

    //Eliminar estudiante de un curso
    fun eliminarEstudianteDeCurso(idEstudiante: Int, idCurso: Int): Boolean {
        val curso = cursos.find { it.id == idCurso }
        val estudiante = curso?.estudiantes?.find { it.id == idEstudiante}
        return if (estudiante != null) {
            curso.estudiantes.remove(estudiante)
            true
        } else {
            false
        }
    }

    //Actualizar estudiante
    fun actualizarEstudiante(idCurso: Int, idEstudiante: Int, nombre: String, edad: Int, email: String, telefono: Int): Boolean {
        val curso = cursos.find { it.id == idCurso }
        var estudiante = curso?.estudiantes?.find { it.id == idEstudiante}
        return if (estudiante != null) {
            estudiante.nombre = nombre
            estudiante.edad = edad
            estudiante.email = email
            estudiante.telefono = telefono
            guardarCambios()
            true
        } else {
            false
        }
    }
    //Guardar cambios en JSON
    private fun guardarCambios() {
        JsonManager.guardarEnJson(cursos)
    }
}