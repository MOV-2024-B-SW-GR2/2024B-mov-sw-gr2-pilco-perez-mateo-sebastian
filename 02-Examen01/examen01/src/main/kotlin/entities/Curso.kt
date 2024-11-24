package org.example.entities

data class Curso (
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val duracion: Int,
    val estudiantes: MutableList<Estudiante> = mutableListOf()
)