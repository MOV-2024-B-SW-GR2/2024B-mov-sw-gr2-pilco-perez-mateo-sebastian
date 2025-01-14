package com.example.crudapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Curso(
    val id: Int = 0, // Siempre presente (generado por el backend, 0 como valor predeterminado)
    val nombre: String,
    val descripcion: String,
    val duracion: Int
) : Parcelable
