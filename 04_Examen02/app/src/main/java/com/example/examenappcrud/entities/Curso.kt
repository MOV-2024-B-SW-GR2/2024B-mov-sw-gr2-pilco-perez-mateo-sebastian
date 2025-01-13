package com.example.examenappcrud.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cursos")
data class Curso(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val duracion: Int
)