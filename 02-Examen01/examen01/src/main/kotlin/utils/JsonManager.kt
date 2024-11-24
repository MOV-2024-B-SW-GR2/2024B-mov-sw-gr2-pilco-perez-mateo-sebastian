package org.example.utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.example.entities.Curso
import java.io.File

object JsonManager {
    private val gson = Gson()
    private const val filePath = "datos.json"

    fun <T> guardarEnJson(datos: T) {
        val jsonString = gson.toJson(datos)
        File(filePath).writeText(jsonString)
    }

    fun cargarDesdeJson(): MutableList<Curso> {
        val file = File(filePath)
        if (!file.exists()) {
            return mutableListOf()
        }
        val jsonString = file.readText()
        val tipo = object : TypeToken <MutableList<Curso>>() {}.type
        return gson.fromJson(jsonString, tipo)
    }
}