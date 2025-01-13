package com.example.examenappcrud.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Estudiantes",
    foreignKeys = [ForeignKey(
        entity = Curso::class,
        parentColumns = ["id"],
        childColumns = ["cursoId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Estudiante(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val edad: Int,
    val email: String,
    val telefono: Int,
    val cursoId: Int
)