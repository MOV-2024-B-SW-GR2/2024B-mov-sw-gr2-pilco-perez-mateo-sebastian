package com.example.crudapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Estudiante(
    val id: Int? = null, // Opcional porque el backend lo genera
    val nombre: String,
    val edad: Int,
    val email: String,
    val telefono: String,
    val curso_id: Int
) : Parcelable
