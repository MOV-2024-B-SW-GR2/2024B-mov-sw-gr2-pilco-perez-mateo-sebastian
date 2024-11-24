package org.example

import org.example.controller.controlador
import org.example.entities.Curso

fun main() {
    val controlador = controlador()

    while (true) {
        println("\n---- Menú ----")
        println("1. Listar Cursos")
        println("2. Agregar Curso")
        println("3. Eliminar Curso")
        println("4. Agregar Estudiante a Curso")
        println("5. Listar Estudiantes de Curso")
        println("6. Eliminar Estudiante de Curso")
        println("7. Actualizar Estudiante")
        println("8. Salir")
        print("Seleccione una opción: ")
        when(readLine()?.toIntOrNull()) {
            1 -> {
                val cursos = controlador.listarCurso()
                if(cursos.isEmpty()) {
                    println("No hay cursos disponibles")
                } else {
                    cursos.forEach { curso ->
                        println("ID: ${curso.id}, Nombre: ${curso.nombre}, Descripción: ${curso.descripcion}")
                    }
                }
            }
            2 -> {
                println("Ingrese el ID del curso: ")
                val id = readLine()?.toIntOrNull()?: return
                println("Ingrese el nombre del curso: ")
                val nombre = readLine()?.toString() ?: ""
                println("Ingrese la descripción del curso: ")
                val descripcion = readLine()?.toString() ?: ""
                println("Ingrese la duración del curso (en meses): ")
                val duracion = readLine()?.toIntOrNull()?:return
                controlador.agregarCurso(id, nombre, descripcion, duracion)
                println("CURSO $nombre agregado con exito!")
            }
            3 -> {
                println("Ingrese el ID del curso a eliminar: ")
                val idCurso = readLine()?.toIntOrNull()?: return
                val eliminado = controlador.eliminarCurso(idCurso)
                if(eliminado) {
                    println("CURSO con id= $idCurso eliminado con EXITO!")
                } else {
                    println("CURSO no encontrado")
                }
            }
            4 -> {
                println("Seleccione el ID del curso para agregar un estudiante: ")
                val idCurso = readLine()?.toIntOrNull()?: return
                println("Ingrese el ID del estudiante: ")
                val idEstudiante = readLine()?.toIntOrNull()?: return
                println("Ingrese el nombre del estudiante: ")
                val nombre = readLine()?.toString() ?: ""
                println("Ingrese la edad del estudiante: ")
                val edad = readLine()?.toIntOrNull()?: return
                println("Ingrese el email del estudiante: ")
                val email = readLine()?.toString() ?: ""
                println("Ingrese el teléfono del estudiante: ")
                val telefono = readLine()?.toIntOrNull()?:return
                controlador.agregarEstudianteACurso(idCurso, idEstudiante, nombre, edad, email, telefono)
                println("Estudiante: $nombre agregado con exito!")
            }
            5 -> {
                println("Seleccione el ID del curso para listar a los estudiantes: ")
                val idCurso = readLine()?.toIntOrNull()?: return
                val estudiantes = controlador.listarEstudiantesCurso(idCurso)
                if(estudiantes.isEmpty()) {
                    println("NO hay estudiantes en este curso")
                } else {
                    estudiantes.forEach { estudiante ->
                        println("ID: ${estudiante.id}, Nombre: ${estudiante.nombre}, Edad: ${estudiante.edad}, Email: ${estudiante.email}")
                    }
                }
            }
            6 -> {
                println("Seleccione el ID del curso para eliminar un estudiante: ")
                val idCurso = readLine()?.toIntOrNull()?: return
                println("Ingrese el ID del estudiante a eliminar:")
                val idEstudiante = readLine()?.toIntOrNull() ?: return
                val eliminado = controlador.eliminarEstudianteDeCurso(idEstudiante, idCurso)
                if(eliminado) {
                    println("Estudiante con el Id: $idEstudiante eliminado con EXITO!")
                } else {
                    println("Estudiante no encontrado!")
                }
            }
            7 -> {
                println("Seleccione el ID del curso para actualizar un estudiante:")
                val idCurso = readLine()?.toIntOrNull() ?: return
                println("Ingrese el ID del estudiante a actualizar:")
                val idEstudiante = readLine()?.toIntOrNull() ?: return
                println("Ingrese el nuevo nombre del estudiante:")
                val nombre = readLine()?.toString() ?: ""
                println("Ingrese la nueva edad del estudiante:")
                val edad = readLine()?.toIntOrNull() ?: return
                println("Ingrese el nuevo email del estudiante:")
                val email = readLine()?.toString() ?: ""
                println("Ingrese el nuevo teléfono del estudiante: ")
                val telefono = readLine()?.toIntOrNull()?:return
                val actualizado = controlador.actualizarEstudiante(idCurso,idEstudiante, nombre, edad, email, telefono)
                if(actualizado) {
                    println("Estudiante $nombre actualizado con EXITO!")
                } else {
                    println("Estudiante no encontrado!")
                }
            }
            8 -> break
            else -> println("OPCION inválida. Intente de nuevo!")
        }
    }
}