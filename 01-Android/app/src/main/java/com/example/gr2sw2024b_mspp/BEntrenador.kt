package com.example.gr2sw2024b_mspp

class BEntrenador (
    var id: Int,
    var nombre: String,
    var descripcion: String?
) {
    override fun toString(): String {
        return "Mi nombre es: $nombre.\nDescripción: ${descripcion}"
    }
}